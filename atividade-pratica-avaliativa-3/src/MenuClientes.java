import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Exemplo simples de menus, painéis, botões, cores, fontes e imagem em Swing.
 */
public final class MenuClientes extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String NOME_ALUNO = "Theilor";

    private final JPanel painelInicial;
    private final JPanel painelCadastro;
    private final JTextField campoNome = new JTextField(20);
    private String nomeSalvo = "";

    public MenuClientes() {
        setTitle("MENU, ITENS DE MENU, FONTES");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon logo = new ImageIcon(getClass().getResource("logo-unifagoc.jpg"));
        setIconImage(logo.getImage());
        painelInicial = criarPainelInicial(logo);
        painelCadastro = criarPainelCadastro();
        setJMenuBar(criarMenus());
        mostrarPainel(painelInicial);
    }

    private JMenuBar criarMenus() {
        JMenuBar barra = new JMenuBar();
        JMenu arquivo = new JMenu("Arquivo");
        arquivo.setFont(new Font("Arial", Font.PLAIN, 14));
        arquivo.add(criarItem("Novo", e -> {
            campoNome.setText("");
            mostrarPainel(painelCadastro);
        }));
        arquivo.add(criarItem("Sair", e -> dispose()));
        barra.add(arquivo);

        JMenu relatorio = new JMenu("Relatório");
        relatorio.setFont(new Font("Arial", Font.PLAIN, 14));
        relatorio.add(criarItem("Cliente", e -> mostrarRelatorioClientes()));
        relatorio.add(criarItem("Fornecedor", e -> mostrarMensagem("Relatório de fornecedores")));
        relatorio.addSeparator();

        // Sobre é um submenu para manter os dois menus principais do enunciado.
        JMenu sobre = new JMenu("Sobre");
        sobre.setFont(new Font("Arial", Font.PLAIN, 14));
        sobre.add(criarItem("Info", e -> mostrarMensagem("Aluno: " + NOME_ALUNO)));
        relatorio.add(sobre);
        barra.add(relatorio);
        return barra;
    }

    private JPanel criarPainelInicial(ImageIcon logo) {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(Color.YELLOW);
        painel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBackground(Color.YELLOW);
        JLabel titulo = new JLabel("CONTROLE DE CLIENTES");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(0, 128, 0));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel imagem = new JLabel(logo);
        imagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(Box.createVerticalGlue());
        centro.add(titulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(imagem);
        centro.add(Box.createVerticalGlue());
        painel.add(centro, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        botoes.setBackground(Color.YELLOW);
        botoes.add(criarBotao("Cliente", e -> mostrarRelatorioClientes()));
        botoes.add(criarBotao("Fornecedor", e -> mostrarMensagem("Relatório de fornecedores")));
        painel.add(botoes, BorderLayout.SOUTH);
        return painel;
    }

    private JPanel criarPainelCadastro() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(new Color(240, 240, 240));
        painel.setBorder(BorderFactory.createTitledBorder("Cadastro de cliente"));

        JPanel campos = new JPanel();
        campos.setBackground(new Color(240, 240, 240));
        JLabel nome = new JLabel("Nome:");
        nome.setFont(new Font("Arial", Font.PLAIN, 14));
        nome.setLabelFor(campoNome);
        campoNome.setFont(new Font("Arial", Font.PLAIN, 14));
        campos.add(nome);
        campos.add(campoNome);
        painel.add(campos, BorderLayout.NORTH);

        JPanel botoes = new JPanel();
        botoes.setBackground(new Color(240, 240, 240));
        botoes.add(criarBotao("Salvar", e -> salvarCliente()));
        botoes.add(criarBotao("Cancelar", e -> {
            campoNome.setText("");
            mostrarMensagem("Cadastro cancelado.");
            mostrarPainel(painelInicial);
        }));
        painel.add(botoes, BorderLayout.SOUTH);
        return painel;
    }

    private JMenuItem criarItem(String texto, ActionListener evento) {
        JMenuItem item = new JMenuItem(texto);
        item.setFont(new Font("Arial", Font.PLAIN, 14));
        item.addActionListener(evento);
        return item;
    }

    private JButton criarBotao(String texto, ActionListener evento) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.PLAIN, 14));
        botao.addActionListener(evento);
        return botao;
    }

    private void mostrarPainel(JPanel painel) {
        setContentPane(painel);
        revalidate();
        repaint();
    }

    private void salvarCliente() {
        String nome = campoNome.getText().trim();
        if (nome.isEmpty()) {
            mostrarMensagem("Informe o nome do cliente.");
            return;
        }
        nomeSalvo = nome;
        mostrarMensagem("Cliente salvo: " + nomeSalvo);
        mostrarPainel(painelInicial);
    }

    private void mostrarRelatorioClientes() {
        String mensagem = "Relatório de clientes";
        if (!nomeSalvo.isEmpty()) {
            mensagem += "\nCliente: " + nomeSalvo;
        }
        mostrarMensagem(mensagem);
    }

    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuClientes().setVisible(true));
    }
}
