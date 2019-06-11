package br.com.uniasselvi.carteira.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T> extends Serializable {

	T salvar(T entidade);

	T atualizar(T entidade);

	List<T> listar();

	T buscarPorId(Serializable id);

	void excluir(T entidade);
}
