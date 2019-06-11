package br.com.uniasselvi.carteira.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.uniasselvi.carteira.util.annotation.Transactional;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {
	private static final long serialVersionUID = 2640261795710483008L;

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();

		Object result = null;

		try {
			if (!trx.isActive()) {
				trx.begin();
				result = context.proceed();
				if (!trx.getRollbackOnly()) {
					trx.commit();
				} else {
					trx.rollback();
				}
			}
		} catch (Exception e) {
			if (trx.isActive()) {
				trx.rollback();
			}
		}

		return result;
	}
}
