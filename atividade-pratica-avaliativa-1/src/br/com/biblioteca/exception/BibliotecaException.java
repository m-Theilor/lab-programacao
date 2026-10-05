package br.com.biblioteca.exception;

/**
 * Classe base para excecoes de negocio da biblioteca.
 * Demonstra o conceito de Excecoes em Java (heranca de Exception).
 */
public class BibliotecaException extends Exception {
    public BibliotecaException(String mensagem) {
        super(mensagem);
    }

    public BibliotecaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
