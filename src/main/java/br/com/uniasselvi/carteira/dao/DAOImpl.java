package br.com.uniasselvi.carteira.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
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

}
