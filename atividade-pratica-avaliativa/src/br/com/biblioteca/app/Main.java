package br.com.biblioteca.app;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.model.Funcionario;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Membro;
import br.com.biblioteca.service.BibliotecaService;

import java.util.Scanner;

/**
 * Ponto de entrada do sistema de Biblioteca.
 * Apresenta um menu interativo no terminal via Scanner e trata excecoes do sistema.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BibliotecaService service = new BibliotecaService();

    public static void main(String[] args) {
        boolean rodando = true;

        System.out.println("==================================================");
        System.out.println("   SISTEMA DE GERENCIAMENTO DE BIBLIOTECA (POO)   ");
        System.out.println("==================================================");

        while (rodando) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    menuLivros();
                    break;
                case 2:
                    menuUsuarios();
                    break;
                case 3:
                    menuEmprestimos();
                    break;
                case 0:
                    System.out.println("\nEncerrando o sistema. Ate logo!");
                    rodando = false;
                    break;
                default:
                    System.out.println("\nOpcao invalida. Digite um numero valido.");
            }
        }

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n---------------- MENU PRINCIPAL ----------------");
        System.out.println("1 - Gerenciamento de Livros");
        System.out.println("2 - Gerenciamento de Membros / Usuarios");
        System.out.println("3 - Gerenciamento de Emprestimos");
        System.out.println("0 - Sair");
        System.out.println("------------------------------------------------");
    }

    // SUBMENU: LIVROS
    private static void menuLivros() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- GERENCIAMENTO DE LIVROS ---");
            System.out.println("1 - Incluir Livro");
            System.out.println("2 - Editar Livro");
            System.out.println("3 - Remover Livro");
            System.out.println("4 - Listar Livros");
            System.out.println("0 - Voltar ao Menu Principal");

            int op = lerInteiro("Opcao: ");
            switch (op) {
                case 1:
                    incluirLivro();
                    break;
                case 2:
                    editarLivro();
                    break;
                case 3:
                    removerLivro();
                    break;
                case 4:
                    BibliotecaService.imprimirColecaoExibivel(service.listarLivros(), "ACERVO DE LIVROS");
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private static void incluirLivro() {
        System.out.println("\n[Novo Livro]");
        String titulo = lerTexto("Titulo: ");
        String autor = lerTexto("Autor: ");
        int ano = lerInteiro("Ano de Publicacao: ");

        try {
            Livro livro = service.adicionarLivro(titulo, autor, ano);
            System.out.println("Sucesso: Livro cadastrado com ID " + livro.getId() + "!");
        } catch (BibliotecaException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    private static void editarLivro() {
        System.out.println("\n[Editar Livro]");
        int id = lerInteiro("Informe o ID do livro a editar: ");
        String novoTitulo = lerTexto("Novo Titulo (ou ENTER para manter): ");
        String novoAutor = lerTexto("Novo Autor (ou ENTER para manter): ");
        String anoStr = lerTexto("Novo Ano (ou 0 para manter): ");
        int novoAno = 0;
        if (!anoStr.trim().isEmpty()) {
            try {
                novoAno = Integer.parseInt(anoStr.trim());
            } catch (NumberFormatException ignored) {}
        }

        try {
            service.editarLivro(id, novoTitulo, novoAutor, novoAno);
            System.out.println("Sucesso: Dados do livro ID " + id + " atualizados!");
        } catch (BibliotecaException e) {
            System.out.println("Erro ao editar livro: " + e.getMessage());
        }
    }

    private static void removerLivro() {
        System.out.println("\n[Remover Livro]");
        int id = lerInteiro("Informe o ID do livro a remover: ");
        try {
            service.removerLivro(id);
            System.out.println("Sucesso: Livro ID " + id + " removido com sucesso!");
        } catch (BibliotecaException e) {
            System.out.println("Erro ao remover livro: " + e.getMessage());
        }
    }

    // SUBMENU: MEMBROS / USUARIOS
    private static void menuUsuarios() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- GERENCIAMENTO DE MEMBROS / USUARIOS ---");
            System.out.println("1 - Cadastrar Membro (Leitor)");
            System.out.println("2 - Cadastrar Funcionario");
            System.out.println("3 - Editar Usuario");
            System.out.println("4 - Listar Usuarios");
            System.out.println("0 - Voltar ao Menu Principal");

            int op = lerInteiro("Opcao: ");
            switch (op) {
                case 1:
                    cadastrarMembro();
                    break;
                case 2:
                    cadastrarFuncionario();
                    break;
                case 3:
                    editarUsuario();
                    break;
                case 4:
                    BibliotecaService.imprimirColecaoExibivel(service.listarUsuarios(), "USUARIOS CADASTRADOS");
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private static void cadastrarMembro() {
        System.out.println("\n[Cadastro de Membro]");
        String nome = lerTexto("Nome: ");
        String email = lerTexto("E-mail: ");
        String tel = lerTexto("Telefone: ");

        try {
            Membro membro = new Membro(0, nome, email, tel);
            service.cadastrarUsuario(membro);
            System.out.println("Sucesso: Membro cadastrado com ID " + membro.getId() + "!");
        } catch (BibliotecaException e) {
            System.out.println("Erro ao cadastrar membro: " + e.getMessage());
        }
    }

    private static void cadastrarFuncionario() {
        System.out.println("\n[Cadastro de Funcionario]");
        String nome = lerTexto("Nome: ");
        String email = lerTexto("E-mail: ");
        String cargo = lerTexto("Cargo: ");
        String mat = lerTexto("Matricula Funcional: ");

        try {
            Funcionario func = new Funcionario(0, nome, email, cargo, mat);
            service.cadastrarUsuario(func);
            System.out.println("Sucesso: Funcionario cadastrado com ID " + func.getId() + "!");
        } catch (BibliotecaException e) {
            System.out.println("Erro ao cadastrar funcionario: " + e.getMessage());
        }
    }

    private static void editarUsuario() {
        System.out.println("\n[Editar Usuario]");
        int id = lerInteiro("Informe o ID do usuario a editar: ");
        String novoNome = lerTexto("Novo Nome (ou ENTER para manter): ");
        String novoEmail = lerTexto("Novo E-mail (ou ENTER para manter): ");

        try {
            service.editarUsuario(id, novoNome, novoEmail);
            System.out.println("Sucesso: Dados do usuario ID " + id + " atualizados!");
        } catch (BibliotecaException e) {
            System.out.println("Erro ao editar usuario: " + e.getMessage());
        }
    }

    // SUBMENU: EMPRESTIMOS
    private static void menuEmprestimos() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- GERENCIAMENTO DE EMPRESTIMOS ---");
            System.out.println("1 - Realizar Emprestimo");
            System.out.println("2 - Encerrar / Devolver Emprestimo");
            System.out.println("3 - Listar Todos os Emprestimos");
            System.out.println("0 - Voltar ao Menu Principal");

            int op = lerInteiro("Opcao: ");
            switch (op) {
                case 1:
                    realizarEmprestimo();
                    break;
                case 2:
                    encerrarEmprestimo();
                    break;
                case 3:
                    BibliotecaService.imprimirColecaoExibivel(service.listarEmprestimos(), "HISTORICO DE EMPRESTIMOS");
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private static void realizarEmprestimo() {
        System.out.println("\n[Realizar Emprestimo]");
        int idLivro = lerInteiro("ID do Livro: ");
        int idUsuario = lerInteiro("ID do Usuario/Membro: ");

        try {
            var emp = service.realizarEmprestimo(idLivro, idUsuario);
            System.out.println("Sucesso: Emprestimo realizado com ID " + emp.getId() + "!");
        } catch (BibliotecaException e) {
            System.out.println("Erro ao realizar emprestimo: " + e.getMessage());
        }
    }

    private static void encerrarEmprestimo() {
        System.out.println("\n[Encerrar / Devolver Emprestimo]");
        int idEmprestimo = lerInteiro("ID do Emprestimo: ");

        try {
            service.encerrarEmprestimo(idEmprestimo);
            System.out.println("Sucesso: Emprestimo ID " + idEmprestimo + " encerrado e livro devolvido ao acervo!");
        } catch (BibliotecaException e) {
            System.out.println("Erro ao encerrar emprestimo: " + e.getMessage());
        }
    }

    // METODOS AUXILIARES DE ENTRADA
    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (!scanner.hasNextLine()) {
                return 0;
            }
            String linha = scanner.nextLine();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida! Por favor, digite um numero inteiro.");
            }
        }
    }
}
