package exBanco;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;

        Banco c1 = new Banco(1, 100, "Sede", 1);

        try {
            while (op != 4 && op != 5) {
                System.out.println("\n==============================================");
                System.out.println("              SISTEMA BANCARIO                ");
                System.out.println("==============================================");
                System.out.println("1 - Creditar");
                System.out.println("2 - Debitar");
                System.out.println("3 - Consultar Saldo");
                System.out.println("4 - Encerrar Conta");
                System.out.println("5 - Sair");
                System.out.print("Escolha uma opcao: ");

                try {
                    op = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Entrada invalida! Por favor, digite um numero entre 1 e 5.");
                    sc.nextLine();
                    op = 0;
                    continue;
                }

                if (op < 1 || op > 5) {
                    System.out.println("Erro: Opcao invalida! Digite um numero de 1 a 5.");
                    continue;
                }

                switch (op) {
                    case 1:
                        if (c1.getTipo() == 4) {
                            System.out.println("Erro: Nao e possivel creditar, conta encerrada!");
                        } else {
                            try {
                                System.out.print("Informe o valor a creditar: R$ ");
                                double valorCredito = Double.parseDouble(sc.next().replace(",", "."));
                                c1.creditar(valorCredito);
                                System.out.printf("Novo saldo: R$ %.2f%n", c1.getSaldo());
                            } catch (NumberFormatException e) {
                                System.out.println("Erro: Valor numerico invalido para credito.");
                            }
                        }
                        break;

                    case 2:
                        if (c1.getTipo() == 4) {
                            System.out.println("Erro: Nao e possivel debitar, conta encerrada!");
                        } else {
                            try {
                                System.out.print("Informe o valor a debitar: R$ ");
                                double valorDebito = Double.parseDouble(sc.next().replace(",", "."));
                                c1.debitar(valorDebito);
                                System.out.printf("Novo saldo: R$ %.2f%n", c1.getSaldo());
                            } catch (NumberFormatException e) {
                                System.out.println("Erro: Valor numerico invalido para debito.");
                            }
                        }
                        break;

                    case 3:
                        System.out.printf("Saldo atual (getSaldo): R$ %.2f%n", c1.getSaldo());
                        System.out.println(c1.consultarSaldo());
                        break;

                    case 4:
                        if (c1.getSaldo() < 0) {
                            System.out.println("Erro: A conta possui saldo negativo e nao pode ser encerrada.");
                            op = 0; 
                        } else {
                            c1.encerrarConta();
                            c1.textoEncerrar();
                            System.out.println("Conta finalizada. Programa encerrando.");
                        }
                        break;

                    case 5:
                        System.out.println("Encerrando o programa. Ate logo!");
                        break;

                    default:
                        System.out.println("Erro: Opcao invalida! Digite um numero de 1 a 5.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado no sistema: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
