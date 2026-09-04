package br.com.biblioteca.model;

/**
 * Representa um Funcionario da biblioteca.
 * Demonstra Heranca estendendo a classe Usuario e Polimorfismo ao sobrescrever metodos.
 */
public class Funcionario extends Usuario {
    private String cargo;
    private String matriculaFuncional;

    public Funcionario(int id, String nome, String email, String cargo, String matriculaFuncional) {
        super(id, nome, email);
        this.cargo = cargo;
        this.matriculaFuncional = matriculaFuncional;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getMatriculaFuncional() {
        return matriculaFuncional;
    }

    public void setMatriculaFuncional(String matriculaFuncional) {
        this.matriculaFuncional = matriculaFuncional;
    }

    @Override
    public String getTipoUsuario() {
        return "Funcionario";
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Cargo: " + cargo);
        System.out.println("Matricula Funcional: " + matriculaFuncional);
        System.out.println("----------------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + " - Cargo: " + cargo + " (Matricula: " + matriculaFuncional + ")";
    }
}
