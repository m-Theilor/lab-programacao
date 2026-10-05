package br.com.biblioteca.service;

import br.com.biblioteca.exception.EntidadeNaoEncontradaException;
import br.com.biblioteca.exception.LivroIndisponivelException;
import br.com.biblioteca.exception.RegraNegocioException;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Exibivel;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Servico central de gerenciamento da Biblioteca.
 * Encapsula as regras de negocio e a manipulacao das colecoes em memoria.
 */
public class BibliotecaService {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    private int proximoIdLivro = 1;
    private int proximoIdUsuario = 1;
    private int proximoIdEmprestimo = 1;

    public BibliotecaService() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    // GERENCIAMENTO DE LIVROS

    /**
     * Adiciona um novo livro ao acervo.
     */
    public Livro adicionarLivro(String titulo, String autor, int anoPublicacao) throws RegraNegocioException {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new RegraNegocioException("O titulo do livro nao pode ser vazio.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new RegraNegocioException("O autor do livro nao pode ser vazio.");
        }
        if (anoPublicacao <= 0) {
            throw new RegraNegocioException("O ano de publicacao deve ser um numero positivo.");
        }

        Livro livro = new Livro(proximoIdLivro++, titulo.trim(), autor.trim(), anoPublicacao);
        livros.add(livro);
        return livro;
    }

    /**
     * Busca um livro pelo identificador.
     */
    public Livro buscarLivroPorId(int id) throws EntidadeNaoEncontradaException {
        for (Livro l : livros) {
            if (l.getId() == id) {
                return l;
            }
        }
        throw new EntidadeNaoEncontradaException("Livro com ID " + id + " nao encontrado.");
    }

    /**
     * Edita os dados de um livro existente.
     */
    public void editarLivro(int id, String novoTitulo, String novoAutor, int novoAno) 
            throws EntidadeNaoEncontradaException, RegraNegocioException {
        Livro livro = buscarLivroPorId(id);

        if (novoTitulo != null && !novoTitulo.trim().isEmpty()) {
            livro.setTitulo(novoTitulo.trim());
        }
        if (novoAutor != null && !novoAutor.trim().isEmpty()) {
            livro.setAutor(novoAutor.trim());
        }
        if (novoAno > 0) {
            livro.setAnoPublicacao(novoAno);
        }
    }

    /**
     * Remove um livro do acervo caso nao esteja atualmente emprestado.
     */
    public void removerLivro(int id) throws EntidadeNaoEncontradaException, RegraNegocioException {
        Livro livro = buscarLivroPorId(id);

        if (!livro.isDisponivel()) {
            throw new RegraNegocioException("Nao e possivel remover o livro ID " + id + " pois ele esta emprestado.");
        }

        livros.remove(livro);
    }

    /**
     * Retorna a lista de livros cadastrados.
     */
    public List<Livro> listarLivros() {
        return new ArrayList<>(livros);
    }

    // GERENCIAMENTO DE MEMBROS / USUARIOS
    
    /**
     * Cadastra um usuario (Membro ou Funcionario) no sistema.
     */
    public void cadastrarUsuario(Usuario usuario) throws RegraNegocioException {
        if (usuario == null) {
            throw new RegraNegocioException("Usuario invalido.");
        }
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do usuario nao pode ser vazio.");
        }

        usuario.setId(proximoIdUsuario++);
        usuarios.add(usuario);
    }

    /**
     * Busca um usuario pelo identificador.
     */
    public Usuario buscarUsuarioPorId(int id) throws EntidadeNaoEncontradaException {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        throw new EntidadeNaoEncontradaException("Usuario com ID " + id + " nao encontrado.");
    }

    /**
     * Edita dados basicos do usuario.
     */
    public void editarUsuario(int id, String novoNome, String novoEmail) throws EntidadeNaoEncontradaException {
        Usuario usuario = buscarUsuarioPorId(id);
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            usuario.setNome(novoNome.trim());
        }
        if (novoEmail != null && !novoEmail.trim().isEmpty()) {
            usuario.setEmail(novoEmail.trim());
        }
    }

    /**
     * Retorna a lista de usuarios cadastrados.
     */
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    // GERENCIAMENTO DE EMPRESTIMOS

    /**
     * Realiza o emprestimo de um livro para um usuario.
     */
    public Emprestimo realizarEmprestimo(int idLivro, int idUsuario) 
            throws EntidadeNaoEncontradaException, LivroIndisponivelException, RegraNegocioException {
        Livro livro = buscarLivroPorId(idLivro);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if (!livro.isDisponivel()) {
            throw new LivroIndisponivelException("O livro '" + livro.getTitulo() + "' ja esta emprestado.");
        }

        // Marca livro como emprestado e cria registro
        livro.setDisponivel(false);
        Emprestimo emprestimo = new Emprestimo(proximoIdEmprestimo++, livro, usuario);
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    /**
     * Busca um emprestimo pelo identificador.
     */
    public Emprestimo buscarEmprestimoPorId(int id) throws EntidadeNaoEncontradaException {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new EntidadeNaoEncontradaException("Emprestimo com ID " + id + " nao encontrado.");
    }

    /**
     * Encerra um emprestimo ativo, liberando o livro para novas retiradas.
     */
    public void encerrarEmprestimo(int idEmprestimo) 
            throws EntidadeNaoEncontradaException, RegraNegocioException {
        Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);

        if (!emprestimo.isAtivo()) {
            throw new RegraNegocioException("O emprestimo ID " + idEmprestimo + " ja foi encerrado anteriormente.");
        }

        emprestimo.encerrar();
    }

    /**
     * Retorna todos os emprestimos (ativos e encerrados).
     */
    public List<Emprestimo> listarEmprestimos() {
        return new ArrayList<>(emprestimos);
    }
    
    // UTILITARIO POLIMORFICO

    /**
     * Demonstra Polimorfismo: imprime qualquer colecao de entidades que implementam Exibivel.
     */
    public static void imprimirColecaoExibivel(List<? extends Exibivel> lista, String titulo) {
        System.out.println("\n=== " + titulo + " ===");
        if (lista.isEmpty()) {
            System.out.println("Nenhum registro cadastrado.");
            return;
        }
        for (Exibivel item : lista) {
            item.exibirDetalhes(); // Chamada polimorfica em tempo de execucao
        }
    }
}
