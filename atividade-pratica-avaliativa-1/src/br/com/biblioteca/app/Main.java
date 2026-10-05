package br.com.biblioteca.app;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Membro;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.BibliotecaService;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Interface Swing simples para as mesmas operações da biblioteca do terminal.
 * Uma única janela troca de painel, mantendo o mesmo serviço durante a execução.
 */
public class Main {
    private final BibliotecaService service = new BibliotecaService();
    private final JFrame janela = new JFrame();

    private final JTextField idLivro = new JTextField();
    private final JTextField tituloLivro = new JTextField();
    private final JTextField autorLivro = new JTextField();
    private final JTextField anoLivro = new JTextField();
    private final JTextArea listaLivros = new JTextArea();

    private final JTextField idUsuario = new JTextField();
    private final JTextField nomeUsuario = new JTextField();
    private final JTextField emailUsuario = new JTextField();
    private final JTextField telefoneMembro = new JTextField();
    private final JTextField cargoFuncionario = new JTextField();
    private final JTextField matriculaFuncionario = new JTextField();
    private final JTextArea listaUsuarios = new JTextArea();

    private final JTextField livroEmprestimo = new JTextField();
    private final JTextField usuarioEmprestimo = new JTextField();
    private final JTextField idEmprestimo = new JTextField();
    private final JTextArea listaEmprestimos = new JTextArea();

    private final JPanel telaLivros;
    private final JPanel telaUsuarios;
    private final JPanel telaEmprestimos;

    public Main() {
        janela.setSize(820, 620);
        janela.setMinimumSize(new Dimension(760, 520));
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        telaLivros = criarTelaLivros();
        telaUsuarios = criarTelaUsuarios();
        telaEmprestimos = criarTelaEmprestimos();
        mostrarMenuPrincipal();
    }

    public static void main(String[] args) {
        // Os componentes Swing são criados na thread de eventos da interface.
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.janela.setVisible(true);
        });
    }

    // NAVEGAÇÃO ENTRE TELAS
    private void mostrarMenuPrincipal() {
        JPanel menu = new JPanel(new GridLayout(5, 1, 10, 10));
        menu.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        menu.add(new JLabel("Sistema de Gerenciamento de Biblioteca", JLabel.CENTER));

        JButton livros = new JButton("Livros");
        livros.addActionListener(e -> {
            listarLivros();
            mostrarTela(telaLivros, "Livros");
        });
        menu.add(livros);

        JButton usuarios = new JButton("Usuários");
        usuarios.addActionListener(e -> {
            listarUsuarios();
            mostrarTela(telaUsuarios, "Usuários");
        });
        menu.add(usuarios);

        JButton emprestimos = new JButton("Empréstimos");
        emprestimos.addActionListener(e -> {
            listarEmprestimos();
            mostrarTela(telaEmprestimos, "Empréstimos");
        });
        menu.add(emprestimos);

        JButton sair = new JButton("Sair");
        sair.addActionListener(e -> janela.dispose());
        menu.add(sair);
        mostrarTela(menu, "Menu Principal");
    }

    private void mostrarTela(JPanel painel, String titulo) {
        janela.setTitle("Biblioteca - " + titulo);
        janela.setContentPane(painel);
        janela.revalidate();
        janela.repaint();
    }

    private JButton criarBotaoVoltar() {
        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> mostrarMenuPrincipal());
        return voltar;
    }

    // TELA E OPERAÇÕES DE LIVROS
    private JPanel criarTelaLivros() {
        JPanel campos = new JPanel(new GridLayout(4, 2, 5, 5));
        adicionarCampo(campos, "ID (para editar ou remover):", idLivro);
        adicionarCampo(campos, "Título:", tituloLivro);
        adicionarCampo(campos, "Autor:", autorLivro);
        adicionarCampo(campos, "Ano de publicação:", anoLivro);

        JPanel botoes = new JPanel();
        JButton incluir = new JButton("Incluir");
        incluir.addActionListener(e -> incluirLivro());
        botoes.add(incluir);
        JButton editar = new JButton("Editar");
        editar.addActionListener(e -> editarLivro());
        botoes.add(editar);
        JButton remover = new JButton("Remover");
        remover.addActionListener(e -> removerLivro());
        botoes.add(remover);
        JButton listar = new JButton("Listar");
        listar.addActionListener(e -> listarLivros());
        botoes.add(listar);
        botoes.add(criarBotaoVoltar());

        return criarTela("Editar: campos vazios mantêm os dados; ano 0 também mantém.",
                campos, botoes, listaLivros);
    }

    private void incluirLivro() {
        try {
            int ano = lerInteiroPositivo(anoLivro, "Ano de publicação");
            Livro livro = service.adicionarLivro(tituloLivro.getText(), autorLivro.getText(), ano);
            listarLivros();
            mostrarSucesso("Livro cadastrado com ID " + livro.getId() + ".");
        } catch (BibliotecaException | IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void editarLivro() {
        try {
            int id = lerInteiroPositivo(idLivro, "ID do livro");
            String textoAno = anoLivro.getText().trim();
            int ano = 0;
            if (!textoAno.isEmpty() && !textoAno.equals("0")) {
                ano = lerInteiroPositivo(anoLivro, "Ano de publicação");
            }
            service.editarLivro(id, tituloLivro.getText(), autorLivro.getText(), ano);
            listarLivros();
            mostrarSucesso("Livro atualizado.");
        } catch (BibliotecaException | IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void removerLivro() {
        try {
            service.removerLivro(lerInteiroPositivo(idLivro, "ID do livro"));
            listarLivros();
            mostrarSucesso("Livro removido.");
        } catch (BibliotecaException | IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void listarLivros() {
        listaLivros.setText("ACERVO DE LIVROS\n\n");
        for (Livro livro : service.listarLivros()) {
            listaLivros.append(livro.toString() + "\n");
        }
        if (service.listarLivros().isEmpty()) {
            listaLivros.append("Nenhum livro cadastrado.");
        }
        listaLivros.setCaretPosition(0);
    }

    // TELA E OPERAÇÕES DE USUÁRIOS
    private JPanel criarTelaUsuarios() {
        JPanel campos = new JPanel(new GridLayout(6, 2, 5, 5));
        adicionarCampo(campos, "ID (para editar):", idUsuario);
        adicionarCampo(campos, "Nome:", nomeUsuario);
        adicionarCampo(campos, "E-mail:", emailUsuario);
        adicionarCampo(campos, "Telefone (membro):", telefoneMembro);
        adicionarCampo(campos, "Cargo (funcionário):", cargoFuncionario);
        adicionarCampo(campos, "Matrícula (funcionário):", matriculaFuncionario);

        JPanel botoes = new JPanel();
        JButton membro = new JButton("Cadastrar Membro");
        membro.addActionListener(e -> cadastrarMembro());
        botoes.add(membro);
        JButton funcionario = new JButton("Cadastrar Funcionário");
        funcionario.addActionListener(e -> cadastrarFuncionario());
        botoes.add(funcionario);
        JButton editar = new JButton("Editar");
        editar.addActionListener(e -> editarUsuario());
        botoes.add(editar);
        JButton listar = new JButton("Listar");
        listar.addActionListener(e -> listarUsuarios());
        botoes.add(listar);
        botoes.add(criarBotaoVoltar());

        return criarTela("Editar altera nome e e-mail; campos vazios mantêm os dados.",
                campos, botoes, listaUsuarios);
    }

    private void cadastrarMembro() {
        try {
            Membro membro = new Membro(0, nomeUsuario.getText(), emailUsuario.getText(),
                    telefoneMembro.getText());
            service.cadastrarUsuario(membro);
            listarUsuarios();
            mostrarSucesso("Membro cadastrado com ID " + membro.getId() + ".");
        } catch (BibliotecaException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void cadastrarFuncionario() {
        try {
            Funcionario funcionario = new Funcionario(0, nomeUsuario.getText(), emailUsuario.getText(),
                    cargoFuncionario.getText(), matriculaFuncionario.getText());
            service.cadastrarUsuario(funcionario);
            listarUsuarios();
            mostrarSucesso("Funcionário cadastrado com ID " + funcionario.getId() + ".");
        } catch (BibliotecaException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void editarUsuario() {
        try {
            int id = lerInteiroPositivo(idUsuario, "ID do usuário");
            service.editarUsuario(id, nomeUsuario.getText(), emailUsuario.getText());
            listarUsuarios();
            mostrarSucesso("Usuário atualizado.");
        } catch (BibliotecaException | IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void listarUsuarios() {
        listaUsuarios.setText("USUÁRIOS CADASTRADOS\n\n");
        for (Usuario usuario : service.listarUsuarios()) {
            listaUsuarios.append(usuario.toString() + "\n");
        }
        if (service.listarUsuarios().isEmpty()) {
            listaUsuarios.append("Nenhum usuário cadastrado.");
        }
        listaUsuarios.setCaretPosition(0);
    }

    // TELA E OPERAÇÕES DE EMPRÉSTIMOS
    private JPanel criarTelaEmprestimos() {
        JPanel campos = new JPanel(new GridLayout(3, 2, 5, 5));
        adicionarCampo(campos, "ID do livro (para emprestar):", livroEmprestimo);
        adicionarCampo(campos, "ID do usuário (para emprestar):", usuarioEmprestimo);
        adicionarCampo(campos, "ID do empréstimo (para devolver):", idEmprestimo);

        JPanel botoes = new JPanel();
        JButton emprestar = new JButton("Realizar Empréstimo");
        emprestar.addActionListener(e -> realizarEmprestimo());
        botoes.add(emprestar);
        JButton devolver = new JButton("Devolver");
        devolver.addActionListener(e -> encerrarEmprestimo());
        botoes.add(devolver);
        JButton listar = new JButton("Listar");
        listar.addActionListener(e -> listarEmprestimos());
        botoes.add(listar);
        botoes.add(criarBotaoVoltar());

        return criarTela("Use os IDs apresentados nas listagens de livros e usuários.",
                campos, botoes, listaEmprestimos);
    }

    private void realizarEmprestimo() {
        try {
            int livro = lerInteiroPositivo(livroEmprestimo, "ID do livro");
            int usuario = lerInteiroPositivo(usuarioEmprestimo, "ID do usuário");
            Emprestimo emprestimo = service.realizarEmprestimo(livro, usuario);
            listarEmprestimos();
            mostrarSucesso("Empréstimo realizado com ID " + emprestimo.getId() + ".");
        } catch (BibliotecaException | IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void encerrarEmprestimo() {
        try {
            service.encerrarEmprestimo(lerInteiroPositivo(idEmprestimo, "ID do empréstimo"));
            listarEmprestimos();
            mostrarSucesso("Empréstimo encerrado e livro devolvido.");
        } catch (BibliotecaException | IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        }
    }

    private void listarEmprestimos() {
        listaEmprestimos.setText("HISTÓRICO DE EMPRÉSTIMOS\n\n");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Emprestimo emprestimo : service.listarEmprestimos()) {
            listaEmprestimos.append(emprestimo.toString() + "\n");
            listaEmprestimos.append("Livro ID: " + emprestimo.getLivro().getId()
                    + " | Usuário ID: " + emprestimo.getUsuario().getId() + "\n");
            listaEmprestimos.append("Data do empréstimo: "
                    + emprestimo.getDataEmprestimo().format(formato) + "\n");
            String devolucao = "Pendente";
            if (emprestimo.getDataDevolucao() != null) {
                devolucao = emprestimo.getDataDevolucao().format(formato);
            }
            listaEmprestimos.append("Data da devolução: " + devolucao + "\n\n");
        }
        if (service.listarEmprestimos().isEmpty()) {
            listaEmprestimos.append("Nenhum empréstimo registrado.");
        }
        listaEmprestimos.setCaretPosition(0);
    }

    // AUXILIARES SIMPLES PARA MONTAR OS PAINÉIS E TRATAR ENTRADAS
    private void adicionarCampo(JPanel painel, String texto, JTextField campo) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setLabelFor(campo);
        painel.add(rotulo);
        painel.add(campo);
    }

    private JPanel criarTela(String orientacao, JPanel campos, JPanel botoes, JTextArea lista) {
        JPanel tela = new JPanel(new BorderLayout(10, 10));
        tela.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel topo = new JPanel(new BorderLayout(5, 5));
        topo.add(new JLabel(orientacao), BorderLayout.NORTH);
        topo.add(campos, BorderLayout.CENTER);
        tela.add(topo, BorderLayout.NORTH);
        lista.setEditable(false);
        lista.setLineWrap(true);
        lista.setWrapStyleWord(true);
        tela.add(new JScrollPane(lista), BorderLayout.CENTER);
        tela.add(botoes, BorderLayout.SOUTH);
        return tela;
    }

    private int lerInteiroPositivo(JTextField campo, String nome) {
        int numero;
        try {
            numero = Integer.parseInt(campo.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nome + " deve ser um número inteiro.");
        }
        if (numero <= 0) {
            throw new IllegalArgumentException(nome + " deve ser maior que zero.");
        }
        return numero;
    }

    private void mostrarSucesso(String mensagem) {
        JOptionPane.showMessageDialog(janela, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(janela, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
