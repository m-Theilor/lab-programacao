import java.util.ArrayList;

public class Veterinario {
    private ArrayList<Animal> animais;
    private ArrayList<Animal> carrocinha;

    public Veterinario() {
        this.animais = new ArrayList<>();
        this.carrocinha = new ArrayList<>();
    }

    public void adicionarAnimal(Animal animal) {
        this.animais.add(animal);
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public ArrayList<Animal> getCarrocinha() {
        return carrocinha;
    }

    public void atenderAnimais() {
        System.out.println("\n--- Atendimento do Veterinario ---");
        for (Animal animal : animais) {
            animal.emitirSom();
            carrocinha.add(animal);
            System.out.println("-> " + animal.getNome() + " foi adicionado(a) a carrocinha.");
        }
    }

    public void listarCarrocinha() {
        System.out.println("\n--- Animais na Carrocinha ---");
        if (carrocinha.isEmpty()) {
            System.out.println("Nenhum animal na carrocinha.");
            return;
        }
        for (Animal animal : carrocinha) {
            System.out.println(animal);
        }
    }
}
