package br.com.uniasselvi.carteira.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface DAO<T> extends Serializable {

	T salvar(T entidade);

	T atualizar(T entidade);

	List<T> listar();

	T buscarPorId(Serializable id);

	void excluir(T entidade);

	List<T> buscarPorHQLConsultaNomeada(String namedQuery, Map<String, Object> params, int maxResults);

	T buscarPorHQLConsultaNomeada(String namedQuery, Map<String, Object> params);
}
