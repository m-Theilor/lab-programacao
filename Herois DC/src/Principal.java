/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Aluno
 */
public class Principal {
    public static void main (String[] args){
        Batman batman = new Batman();
        
        batman.setNome ("Batman");
        batman.poder = "Dinheiro";
        batman.vida = 40;
        batman.velocidade = 15;
        batman.locomocao = "Batmovel";
        batman.riqueza = 1000000000;
        batman.mordomo = "Alfred";
        
        batman.apresentar();
        batman.fraseEfeito();
        
        Heroi superman = new Heroi();
        
        superman.setNome ("Superman");
        superman.poder = "Muita coisa";
        superman.vida = 1000;
        superman.velocidade = 1000;
        
        superman.apresentar();
        
    }
}
