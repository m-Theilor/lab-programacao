package br.com.biblioteca.model;

/**
 * Classe abstrata base que representa um Usuario do sistema.
 * Demonstra os conceitos de Abstracao, Encapsulamento e implementa Exibivel.
 */
public abstract class Usuario implements Exibivel {
    private int id;
    private String nome;
    private String email;

    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters (Encapsulamento)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo abstrato para identificar a categoria do usuario.
     * Deve ser implementado pelas subclasses concretas (Abstracao e Polimorfismo).
     */
    public abstract String getTipoUsuario();

    /**
     * Implementacao base de exibirDetalhes (Polimorfismo e Reuso).
     */
    @Override
    public void exibirDetalhes() {
        System.out.println("----------------------------------------");
        System.out.println("Tipo: " + getTipoUsuario());
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("E-mail: " + email);
    }

    @Override
    public String toString() {
        return String.format("[%s #%d] %s (%s)", getTipoUsuario(), id, nome, email);
    }
}
