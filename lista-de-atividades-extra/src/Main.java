public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("           PROJETO ANIMAIS - REVISAO POO      ");
        System.out.println("==============================================");

        Animal rex = new Cachorro("Rex", 3, "Caramelo");
        Animal mimi = new Gato("Mimi", 2, "Branco");
        Animal peDePano = new Cavalo("Pe de Pano", 5, "Marrom");
        Animal perninha = new Coelho("Perninha", 1, "Cinza");

        System.out.println("\n--- Animais Criados ---");
        System.out.println(rex);
        System.out.println(mimi);
        System.out.println(peDePano);
        System.out.println(perninha);

        System.out.println("\n--- Demonstracao do Item 04 (Modificar Som) ---");
        System.out.println("Som original do " + rex.getNome() + ":");
        rex.emitirSom();

        System.out.println("Modificando o som do " + rex.getNome() + " para 'Woof! Woof! (latido americano)'...");
        rex.modificarSom("Woof! Woof! (latido americano)");

        System.out.println("Novo som do " + rex.getNome() + ":");
        rex.emitirSom();

        Veterinario vet = new Veterinario();
        vet.adicionarAnimal(rex);
        vet.adicionarAnimal(mimi);
        vet.adicionarAnimal(peDePano);
        vet.adicionarAnimal(perninha);

        vet.atenderAnimais();

        vet.listarCarrocinha();

        System.out.println("\nExecucao concluida com sucesso!");
    }
}
