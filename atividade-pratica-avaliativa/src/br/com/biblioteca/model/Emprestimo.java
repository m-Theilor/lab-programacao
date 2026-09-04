package br.com.biblioteca.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representa o registro de um Emprestimo de livro para um usuario.
 * Demonstra Associacao entre classes e implementa a interface Exibivel.
 */
public class Emprestimo implements Exibivel {
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean ativo;

    public Emprestimo(int id, Livro livro, Usuario usuario) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = null;
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    /**
     * Encerra o emprestimo, definindo a data de devolucao e liberando o livro.
     */
    public void encerrar() {
        this.ativo = false;
        this.dataDevolucao = LocalDate.now();
        this.livro.setDisponivel(true);
    }

    @Override
    public void exibirDetalhes() {
        String dataDevStr = (dataDevolucao != null) ? dataDevolucao.format(FORMATADOR) : "Pendente";
        String status = ativo ? "Ativo" : "Encerrado";

        System.out.println("----------------------------------------");
        System.out.println("Emprestimo ID: " + id);
        System.out.println("Status: " + status);
        System.out.println("Livro: [" + livro.getId() + "] " + livro.getTitulo());
        System.out.println("Usuario: [" + usuario.getId() + "] " + usuario.getNome() + " (" + usuario.getTipoUsuario() + ")");
        System.out.println("Data Emprestimo: " + dataEmprestimo.format(FORMATADOR));
        System.out.println("Data Devolucao: " + dataDevStr);
        System.out.println("----------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("Emprestimo #%d | Livro: %s | Usuario: %s | Status: %s",
                id, livro.getTitulo(), usuario.getNome(), (ativo ? "Ativo" : "Devolvido"));
    }
}
