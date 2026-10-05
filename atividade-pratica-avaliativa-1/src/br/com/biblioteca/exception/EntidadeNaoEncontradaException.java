package br.com.biblioteca.exception;

/**
 * Excecao lancada quando uma entidade pesquisada (Livro, Usuario, Emprestimo) nao e localizada.
 */
public class EntidadeNaoEncontradaException extends BibliotecaException {
    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
