package br.com.agenda.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

/**
 * 
 * @author bruno.calmon
 *
 * @param <T>
 */
public abstract class DAO<T> {
	
	private static final Logger LOGGER = Logger.getLogger(DAO.class);
	
	@PersistenceContext
	EntityManager em;

	/**
	 * 
	 * @param entidade
	 */
	public void inserir(T entidade) {
		em.persist(entidade);
	}

	/**
	 * 
	 * @param entidade
	 */
	public void atualizar(T entidade) {
		em.merge(entidade);
	}

	/**
	 * 
	 * @param entidade
	 */
	public void remover(T entidade) {
		em.remove(entidade);
	}

	/**
	 * 
	 * @param entidade
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(T entidade) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT e FROM :entidade e ");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("entidade", entidade);
			return query.getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return Collections.emptyList();
		}
	}
}
