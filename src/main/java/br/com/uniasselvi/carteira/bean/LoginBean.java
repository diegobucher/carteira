package br.com.uniasselvi.carteira.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uniasselvi.carteira.dao.DAO;
import br.com.uniasselvi.carteira.entidade.Usuario;
import br.com.uniasselvi.carteira.util.FacesUtil;

@Named
@ViewScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = -8514695946620325326L;

	@Inject
	private DAO<Usuario> dao;

	private Usuario usuario;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public String buscarUsuario() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("login", usuario.getLogin());
		params.put("senha", usuario.getSenha());
		usuario = dao.buscarPorHQLConsultaNomeada("Usuario.buscarPorLoginSenha", params);

		if (usuario == null) {
			usuario = new Usuario();
			FacesUtil.addErrorMessage("Usuário não encontrado!");
			return null;
		} else {
			return "/main";
		}
	}

	// --------------------------------------------

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
