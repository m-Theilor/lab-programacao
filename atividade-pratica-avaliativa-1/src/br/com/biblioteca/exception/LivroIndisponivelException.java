package br.com.biblioteca.exception;

/**
 * Excecao lancada quando ha tentativa de emprestar um livro que ja se encontra emprestado.
 */
public class LivroIndisponivelException extends BibliotecaException {
    public LivroIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
