public class Coelho extends Animal {

    public Coelho(String nome, int idade, String cor) {
        super(nome, idade, cor, "Chii chii!");
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " (Coelho) fungando/chiando: " + getSom());
    }
}
