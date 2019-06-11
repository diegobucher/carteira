package br.com.uniasselvi.carteira.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DAOFactory {

	@Inject
	private EntityManager em;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Produces
	@Dependent
	public <T> DAO<T> createDAO(InjectionPoint point) {
		ParameterizedType type = (ParameterizedType) point.getType();
		Class classe = (Class) type.getActualTypeArguments()[0];
		return new DAOImpl<T>(classe, em);
	}
}
