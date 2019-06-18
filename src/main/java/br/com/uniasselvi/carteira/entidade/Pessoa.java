package br.com.uniasselvi.carteira.entidade;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa extends Entidade {
	private static final long serialVersionUID = 6227684454612187237L;

	@Column(length = 14, nullable = false)
	private String cpf;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 100, nullable = false)
	private String sobrenome;

	@Column(nullable = false)
	private LocalDate nascimento;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 15, nullable = false)
	private String telefone;

	@Column(length = 100, nullable = false)
	private String endereco;

	@Column(nullable = false)
	private Boolean representante = Boolean.FALSE;

	@Column(nullable = false)
	private char sexo;

	@Column(length = 30)
	private String profissao;

	@Column(length = 15, nullable = false)
	private String estadoCivil;

	@Column(nullable = false)
	private Boolean ativo = Boolean.TRUE;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Boolean getRepresentante() {
		return representante;
	}

	public void setRepresentante(Boolean representante) {
		this.representante = representante;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
