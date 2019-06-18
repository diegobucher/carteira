package br.com.uniasselvi.carteira.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uniasselvi.carteira.dao.DAO;
import br.com.uniasselvi.carteira.entidade.Pedido;

@Named
@ViewScoped
public class PedidoConsultaBean implements Serializable {
	private static final long serialVersionUID = -8514695946620325326L;

	@Inject
	private DAO<Pedido> dao;

	private List<Pedido> pedidos;
	private String cliente;

	@PostConstruct
	public void init() {
		setPedidos(dao.listar());
	}

	public void pesquisar() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nome", cliente);
		setPedidos(dao.buscarPorHQLConsultaNomeada("Pedido.buscarPorNome", params, 0));
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
