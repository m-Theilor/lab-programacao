/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
/**
 *
 * @author Aluno
 */
public class Biblioteca {
    
    private ArrayList<Livro> livros;
    
    public Biblioteca(){
        this.livros = new ArrayList<>();
    }
    
    public void adicionarLivro(Livro livro){
        this.livros.add(livro);
    }
    
    public void listarLivros(){
        for (Livro livro : this.livros){
            livro.descricao();
            System.out.println("--------------------");
        }
    }
    
    public Livro buscarLivroPorTitulo(String titulo){
        for (Livro livro : this.livros){
            if (livro.getTitulo().equalsIgnoreCase(titulo)){
                return livro;
            }
        }
        return null;
    }
}
