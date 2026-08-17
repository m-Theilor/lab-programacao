/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aluno
 */
public class Main {
    public static void main(String[] args){
        Biblioteca biblioteca = new Biblioteca();
        
        LivroFisico livro1 = new LivroFisico("Dom Casmurro", "Machado de Assis", 256, 0.45);
        Ebook livro2 = new Ebook("A Odisseia", "Homero", 304, 35);
        
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        
        System.out.println("=== Listagem de Livros ===");
        biblioteca.listarLivros();
        
        System.out.println("=== Buscar livro ===");
        String tituloLivro = "A Odisseia";
        Livro livroEncontrado = biblioteca.buscarLivroPorTitulo(tituloLivro);
        
        if (livroEncontrado != null){
            System.out.println("Livro encontrado:");
            livroEncontrado.descricao();
        } else {
            System.out.println("Livro com o titulo " + tituloLivro + "não foi encontrado.");
        }
    }
}
