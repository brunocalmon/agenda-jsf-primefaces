package br.com.agenda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class DAO<T> {
	@PersistenceContext
	EntityManager em;

	public void inserir(T entidade) {
		em.persist(entidade);
	}

	public void atualizar(T entidade) {
		em.merge(entidade);
	}

	public void remover(T entidade) {
		em.remove(entidade);
	}

	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(T entidade) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT e FROM :entidade e ");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("entidade", entidade);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
