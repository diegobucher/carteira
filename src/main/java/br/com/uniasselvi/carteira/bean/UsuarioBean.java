package br.com.uniasselvi.carteira.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.uniasselvi.carteira.dao.DAO;
import br.com.uniasselvi.carteira.entidade.Pessoa;
import br.com.uniasselvi.carteira.entidade.Usuario;
import br.com.uniasselvi.carteira.util.FacesUtil;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = -8514695946620325326L;

	@Inject
	private DAO<Usuario> dao;
	@Inject
	private DAO<Pessoa> pessoaDAO;

	private Usuario usuario;
	private Usuario usuarioExcluir;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosFiltrado;
	private List<Pessoa> pessoas;

	@PostConstruct
	public void init() {
		listar();
	}

	public void adicionar() {
		usuario = new Usuario();
	}

	public void editar(Usuario usuario) {
		this.usuario = usuario;
	}

	@Transactional
	public void salvar() {
		if (usuario.getId() != null)
			dao.atualizar(usuario);
		else
			dao.salvar(usuario);
		FacesUtil.addSuccessMessage("Salvo com sucesso");
		listar();
	}

	public void listar() {
		setPessoas(pessoaDAO.listar());
		setUsuarios(dao.listar());
		usuario = null;
	}

	public void voltar() {
		usuario = null;
	}

	public void selecionarExcluir(Usuario usuario) {
		this.usuarioExcluir = usuario;
	}

	@Transactional
	public void excluir() {
		dao.excluir(this.usuarioExcluir);
		FacesUtil.addSuccessMessage("Excluído com sucesso");
		init();
	}

	// --------------------------------------------

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioExcluir() {
		return usuarioExcluir;
	}

	public void setUsuarioExcluir(Usuario usuarioExcluir) {
		this.usuarioExcluir = usuarioExcluir;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuariosFiltrado() {
		return usuariosFiltrado;
	}

	public void setUsuariosFiltrado(List<Usuario> usuariosFiltrado) {
		this.usuariosFiltrado = usuariosFiltrado;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
