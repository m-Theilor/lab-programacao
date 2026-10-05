package br.com.biblioteca.exception;

/**
 * Excecao lancada em violacoes de regras de negocio (ex.: dados invalidos, devolucao duplicada).
 */
public class RegraNegocioException extends BibliotecaException {
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
