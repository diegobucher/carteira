package br.com.uniasselvi.carteira.entidade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
@NamedQuery(name = "Pedido.buscarPorNome", query = "select p from Pedido p where p.cliente.nome like :nome")
public class Pedido extends Entidade {
	private static final long serialVersionUID = 8782375727696566321L;

	@Column(nullable = false)
	private LocalDateTime data;

	@Column(name = "data_entrega")
	private LocalDate dataEntrega;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Pessoa cliente;

	@ManyToMany
	@JoinTable(name = "pedido_produto", joinColumns = { @JoinColumn(name = "pedido_id") }, inverseJoinColumns = { @JoinColumn(name = "produto_id") })
	private List<Produto> produtos;

	@Column(nullable = false)
	private char situacao;

	@Column(name = "valor_total", nullable = false)
	private Double valorTotal;

	@Column(length = 200)
	private String observacao;

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public char getSituacao() {
		return situacao;
	}

	public void setSituacao(char situacao) {
		this.situacao = situacao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
