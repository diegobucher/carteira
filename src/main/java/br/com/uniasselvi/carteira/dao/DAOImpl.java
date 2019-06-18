package br.com.uniasselvi.carteira.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class DAOImpl<T> implements DAO<T> {
	private static final long serialVersionUID = 5465341815730439656L;

	@PersistenceContext
	private EntityManager em;

	private final Class<T> classe;

	public DAOImpl(Class<T> classe, EntityManager em) {
		this.classe = classe;
		this.em = em;
	}

	@Override
	public T salvar(T entidade) {
		em.persist(entidade);
		em.flush();
		return entidade;
	}

	@Override
	public T atualizar(T entidade) {
		entidade = em.merge(entidade);
		em.flush();
		return entidade;
	}

	@Override
	public List<T> listar() {
		TypedQuery<T> query = em.createQuery("from " + classe.getSimpleName(), classe);
		return query.getResultList();
	}

	@Override
	public T buscarPorId(Serializable id) {
		return em.find(classe, id);
	}

	@Override
	public void excluir(T entidade) {
		em.remove(em.contains(entidade) ? entidade : em.merge(entidade));
		em.flush();
	}

	@Override
	public List<T> buscarPorHQLConsultaNomeada(String namedQuery, Map<String, Object> params, int maxResults) {
		TypedQuery<T> query = em.createNamedQuery(namedQuery, this.classe);

		for (Map.Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		return maxResults == 0 ? query.getResultList() : query.setMaxResults(maxResults).getResultList();
	}

	@Override
	public T buscarPorHQLConsultaNomeada(String namedQuery, Map<String, Object> params) {
		try {
			TypedQuery<T> query = em.createNamedQuery(namedQuery, this.classe);

			for (Map.Entry<String, Object> param : params.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
