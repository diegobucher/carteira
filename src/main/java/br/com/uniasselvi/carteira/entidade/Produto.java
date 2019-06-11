package br.com.uniasselvi.carteira.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Produto extends Entidade {
	private static final long serialVersionUID = -9168456375851082902L;

	@Column(length = 50, nullable = false)
	private String descricao;

	@Column(length = 30, nullable = false)
	private String marca;

	@Column(length = 25, nullable = false)
	private String embalagem;

	@Column(length = 20, nullable = false)
	private String tipo;

	@Column(nullable = false)
	private Double preco;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
