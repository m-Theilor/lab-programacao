public class Cavalo extends Animal {

    public Cavalo(String nome, int idade, String cor) {
        super(nome, idade, cor, "Relincho / Iiirrrri!");
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " (Cavalo) relinchando: " + getSom());
    }
}
