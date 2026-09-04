package br.com.biblioteca.model;

/**
 * Representa um Membro (leitor/cliente) da biblioteca.
 * Demonstra Heranca estendendo a classe Usuario e Polimorfismo ao sobrescrever metodos.
 */
public class Membro extends Usuario {
    private String telefone;
    private int limiteEmprestimos;

    public Membro(int id, String nome, String email, String telefone) {
        super(id, nome, email);
        this.telefone = telefone;
        this.limiteEmprestimos = 3; // Limite padrao de emprestimos simultaneos
    }

    public Membro(int id, String nome, String email, String telefone, int limiteEmprestimos) {
        super(id, nome, email);
        this.telefone = telefone;
        this.limiteEmprestimos = limiteEmprestimos;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public void setLimiteEmprestimos(int limiteEmprestimos) {
        this.limiteEmprestimos = limiteEmprestimos;
    }

    @Override
    public String getTipoUsuario() {
        return "Membro";
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Telefone: " + telefone);
        System.out.println("Limite de Emprestimos: " + limiteEmprestimos);
        System.out.println("----------------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + " - Tel: " + telefone + " (Limite: " + limiteEmprestimos + ")";
    }
}
