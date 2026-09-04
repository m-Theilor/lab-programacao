package exBanco;

public class Banco {
    private int numero;
    private int ag_numero;
    private String ag_nome;
    private int tipo;
    private double saldo;

    public Banco() {
    }

    public Banco(int numero, int ag_numero, String ag_nome, int tipo) {
        this.numero = numero;
        this.ag_numero = ag_numero;
        this.ag_nome = ag_nome;
        this.tipo = tipo;
        this.saldo = 0.0;
    }

    public int getNumero() {
        return numero;
    }

    public int getAg_numero() {
        return ag_numero;
    }

    public String getAg_nome() {
        return ag_nome;
    }

    public int getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setAg_numero(int ag_numero) {
        this.ag_numero = ag_numero;
    }

    public void setAg_nome(String ag_nome) {
        this.ag_nome = ag_nome;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void creditar(double valor) {
        if (this.tipo != 4) {
            if (valor > 0) {
                this.saldo += valor;
            } else {
                System.out.println("Erro: O valor para credito deve ser maior que zero.");
            }
        } else {
            System.out.println("Erro: Conta encerrada. Nao e possivel creditar.");
        }
    }

    public void debitar(double valor) {
        if (this.tipo != 4) {
            if (valor > 0) {
                this.saldo -= valor;
            } else {
                System.out.println("Erro: O valor para debito deve ser maior que zero.");
            }
        } else {
            System.out.println("Erro: Conta encerrada. Nao e possivel debitar.");
        }
    }

    public String consultarSaldo() {
        return "Conta: " + this.numero + " | Saldo atual: R$ " + String.format("%.2f", this.saldo);
    }

    public String consultarSaldo(int numConta) {
        if (this.numero == numConta) {
            return "Conta: " + this.numero + " | Saldo atual: R$ " + String.format("%.2f", this.saldo);
        } else {
            return "Conta " + numConta + " nao encontrada.";
        }
    }

    public int encerrarConta() {
        this.tipo = 4;
        return this.tipo;
    }

    public void textoEncerrar() {
        System.out.println("--- Conta Encerrada ---");
        System.out.println("Numero da Conta: " + this.numero);
        System.out.println("Tipo da Conta: " + this.tipo + " (Encerrada)");
        System.out.printf("Saldo na data de encerramento: R$ %.2f%n", this.saldo);
        this.saldo = 0.0;
    }
}
