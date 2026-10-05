package br.com.biblioteca.model;

/**
 * Representa um livro no acervo da biblioteca.
 * Demonstra o conceito de Encapsulamento e implementa a interface Exibivel.
 */
public class Livro implements Exibivel {
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean disponivel;

    public Livro(int id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
    }

    // Getters e Setters (Encapsulamento)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Implementacao do metodo da interface Exibivel (Polimorfismo).
     */
    @Override
    public void exibirDetalhes() {
        String status = disponivel ? "Disponivel" : "Emprestado";
        System.out.println("----------------------------------------");
        System.out.println("ID: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Ano: " + anoPublicacao);
        System.out.println("Status: " + status);
        System.out.println("----------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%d) | %s", 
                id, titulo, autor, anoPublicacao, (disponivel ? "Disponivel" : "Emprestado"));
    }
}
