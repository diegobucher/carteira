package br.com.uniasselvi.carteira.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;

import br.com.uniasselvi.carteira.dao.DAO;
import br.com.uniasselvi.carteira.entidade.Pedido;
import br.com.uniasselvi.carteira.entidade.Pessoa;
import br.com.uniasselvi.carteira.entidade.Produto;
import br.com.uniasselvi.carteira.util.FacesUtil;

@Named
@ViewScoped
public class PedidoBean implements Serializable {
	private static final long serialVersionUID = -8514695946620325326L;

	@Inject
	private DAO<Pedido> dao;
	@Inject
	private DAO<Pessoa> clienteDAO;
	@Inject
	private DAO<Produto> produtoDAO;

	private Pedido pedido;
	private Pedido pedidoExcluir;
	private List<Pedido> pedidos;
	private List<Pedido> pedidosFiltrado;
	private List<Pessoa> clientes;
	private List<Produto> produtos;
	private List<Produto> produtosSelecionado;
	private Integer quantidade;

	@PostConstruct
	public void init() {
		listar();
	}

	public void adicionar() {
		pedido = new Pedido();
	}

	public void editar(Pedido pedido) {
		this.pedido = pedido;
	}

	@Transactional
	public void salvar() {
		if (pedido.getId() != null)
			dao.atualizar(pedido);
		else {
			pedido.setData(LocalDateTime.now());
			pedido.setProdutos(produtosSelecionado);
			dao.salvar(pedido);
		}
		FacesUtil.addSuccessMessage("Salvo com sucesso");
		listar();
	}

	public void listar() {
		setClientes(clienteDAO.listar());
		setProdutos(produtoDAO.listar());
		setPedidos(dao.listar());
		pedido = null;
	}

	public void voltar() {
		pedido = null;
	}

	public void selecionarExcluir(Pedido pedido) {
		this.pedidoExcluir = pedido;
	}

	@Transactional
	public void excluir() {
		dao.excluir(this.pedidoExcluir);
		FacesUtil.addSuccessMessage("Excluído com sucesso");
		init();
	}

	public String getNow() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}

	public void atualizarValor(Produto produto) {
		pedido.setValorTotal(ObjectUtils.defaultIfNull(pedido.getValorTotal(), NumberUtils.DOUBLE_ZERO) + (produto.getPreco() * quantidade));
	}

	// --------------------------------------------

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedidoExcluir() {
		return pedidoExcluir;
	}

	public void setPedidoExcluir(Pedido pedidoExcluir) {
		this.pedidoExcluir = pedidoExcluir;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Pedido> getPedidosFiltrado() {
		return pedidosFiltrado;
	}

	public void setPedidosFiltrado(List<Pedido> pedidosFiltrado) {
		this.pedidosFiltrado = pedidosFiltrado;
	}

	public List<Pessoa> getClientes() {
		return clientes;
	}

	public void setClientes(List<Pessoa> clientes) {
		this.clientes = clientes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutosSelecionado() {
		return produtosSelecionado;
	}

	public void setProdutosSelecionado(List<Produto> produtosSelecionado) {
		this.produtosSelecionado = produtosSelecionado;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
