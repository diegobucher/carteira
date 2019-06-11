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
import br.com.uniasselvi.carteira.entidade.Pessoa;
import br.com.uniasselvi.carteira.util.FacesUtil;

@Named
@ViewScoped
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = -7447644745443228719L;

	@Inject
	private DAO<Pessoa> dao;

	private Pessoa cliente;
	private Pessoa clienteExcluir;
	private List<Pessoa> clientes;
	private List<Pessoa> clientesFiltrado;

	@PostConstruct
	public void init() {
		listar();
	}

	public void adicionar() {
		cliente = new Pessoa();
	}

	public void editar(Pessoa cliente) {
		this.cliente = cliente;
	}

	@Transactional
	public void salvar() {
		if (cliente.getId() != null)
			dao.atualizar(cliente);
		else
			dao.salvar(cliente);

		FacesUtil.addSuccessMessage("Salvo com sucesso");
		listar();
	}

	public void listar() {
		setClientes(dao.listar());
		cliente = null;
	}

	public void voltar() {
		cliente = null;
	}

	public void selecionarExcluir(Pessoa cliente) {
		this.clienteExcluir = cliente;
	}

	@Transactional
	public void excluir() {
		dao.excluir(this.clienteExcluir);
		FacesUtil.addSuccessMessage("Excluído com sucesso");
		init();
	}

	public String getNow() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}

	// --------------------------------------------

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public List<Pessoa> getClientes() {
		return clientes;
	}

	public void setClientes(List<Pessoa> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getClientesFiltrado() {
		return clientesFiltrado;
	}

	public void setClientesFiltrado(List<Pessoa> clientesFiltrado) {
		this.clientesFiltrado = clientesFiltrado;
	}

	public Pessoa getClienteExcluir() {
		return clienteExcluir;
	}

	public void setClienteExcluir(Pessoa clienteExcluir) {
		this.clienteExcluir = clienteExcluir;
	}

}
