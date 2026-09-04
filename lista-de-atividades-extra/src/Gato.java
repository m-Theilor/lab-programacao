public class Gato extends Animal {

    public Gato(String nome, int idade, String cor) {
        super(nome, idade, cor, "Miau!");
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " (Gato) miando: " + getSom());
    }
}
