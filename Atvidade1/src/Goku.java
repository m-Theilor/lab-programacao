/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aluno
 */
public class Goku extends Heroi{
    
    private String transformacao;
    private String poderExtra;
    private double nivelPoder;
    
    public void transformar(){
        System.out.println("Goku transformou em: " + transformacao);
    }

    public String getTransformacao() {
        return transformacao;
    }

    public void setTransformacao(String transformacao) {
        this.transformacao = transformacao;
    }

    public String getPoderExtra() {
        return poderExtra;
    }

    public void setPoderExtra(String poderExtra) {
        this.poderExtra = poderExtra;
    }

    public double getNivelPoder() {
        return nivelPoder;
    }

    public void setNivelPoder(double nivelPoder) {
        this.nivelPoder = nivelPoder;
    }

}