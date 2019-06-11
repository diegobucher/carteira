package br.com.uniasselvi.carteira.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.uniasselvi.carteira.dao.DAO;
import br.com.uniasselvi.carteira.entidade.Produto;
import br.com.uniasselvi.carteira.util.FacesUtil;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = -8031681829339743955L;

	@Inject
	private DAO<Produto> dao;

	private Produto produto;
	private Produto produtoExcluir;
	private List<Produto> produtos;
	private List<Produto> produtosFiltrado;

	@PostConstruct
	public void init() {
		listar();
	}

	public void adicionar() {
		produto = new Produto();
	}

	public void editar(Produto produto) {
		this.produto = produto;
	}

	@Transactional
	public void salvar() {
		if (produto.getId() != null)
			dao.atualizar(produto);
		else
			dao.salvar(produto);

		FacesUtil.addSuccessMessage("Salvo com sucesso");
		listar();
	}

	public void listar() {
		setProdutos(dao.listar());
		produto = null;
	}

	public void voltar() {
		produto = null;
	}

	public void selecionarExcluir(Produto produto) {
		this.produtoExcluir = produto;
	}

	@Transactional
	public void excluir() {
		dao.excluir(this.produtoExcluir);
		FacesUtil.addSuccessMessage("Excluído com sucesso");
		init();
	}

	public String getNow() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}

	// --------------------------------------------

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProdutoExcluir() {
		return produtoExcluir;
	}

	public void setProdutoExcluir(Produto produtoExcluir) {
		this.produtoExcluir = produtoExcluir;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutosFiltrado() {
		return produtosFiltrado;
	}

	public void setProdutosFiltrado(List<Produto> produtosFiltrado) {
		this.produtosFiltrado = produtosFiltrado;
	}

}
