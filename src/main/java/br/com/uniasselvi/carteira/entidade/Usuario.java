package br.com.uniasselvi.carteira.entidade;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.buscarPorLoginSenha", query = "select u from Usuario u where u.login = :login and u.senha = :senha")
public class Usuario extends Entidade {
	private static final long serialVersionUID = -6233933467277295304L;

	@Column(length = 15, nullable = false, unique = true)
	private String login;

	@Column(length = 15, nullable = false)
	private String senha;

	private LocalDateTime ultimoAcesso;

	@OneToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDateTime getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(LocalDateTime ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
