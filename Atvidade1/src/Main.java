/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aluno
 */
public class Main {
    public static void main (String[] args){
        
        Goku goku = new Goku();
        goku.setNome("Goku");
        goku.setVida(1000);
        goku.setEnergia(1000);
        goku.setAtaque("Kamehamehaaaaa");
        goku.setNivelPoder(8001);
        goku.setPoderExtra("Kaioken");
        goku.setTransformacao("SSJ 2");
        goku.apresentar();
        goku.atacar();
        goku.transformar();
        
        HomemDeFerro homemdeferro = new HomemDeFerro();
        homemdeferro.setNome("Homem de Ferro");
        homemdeferro.setVida(100);
        homemdeferro.setEnergia(200);
        homemdeferro.setAtaque("Raio Propulsor");
        homemdeferro.setArmadura("Mark IV");
        homemdeferro.setDinheiro(1000000000);
        homemdeferro.apresentar();
        homemdeferro.atacar();
        homemdeferro.criar();
        
        Hulk hulk = new Hulk();
        hulk.setNome("Hulk");
        hulk.setVida(1000);
        hulk.setEnergia(1000);
        hulk.setAtaque("Smash");
        hulk.setCor("Verde");
        hulk.setFontePoder("Raiva");
        hulk.apresentar();
        hulk.atacar();
    }
}
