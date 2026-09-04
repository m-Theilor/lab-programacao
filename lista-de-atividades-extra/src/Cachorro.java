public class Cachorro extends Animal {

    public Cachorro(String nome, int idade, String cor) {
        super(nome, idade, cor, "Au au!");
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " (Cachorro) latindo: " + getSom());
    }
}
