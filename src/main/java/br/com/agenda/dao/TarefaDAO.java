package br.com.agenda.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Tarefa;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class TarefaDAO extends DAO<Tarefa> {
	
	private static final Logger LOGGER = Logger.getLogger(DAO.class);

	/**
	 * 
	 * @param tarefa
	 * @return List<Tarefa>
	 */
	@SuppressWarnings("unchecked")
	public List<Tarefa> buscarTarefaPorNome(Tarefa tarefa) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT c FROM Tarefa c ");
		sql.append(" WHERE LOWER(c.noTarefa) LIKE LOWER(CONCAT('%', :nome, '%')))");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("nome", tarefa.getNoTarefa());
			return (List<Tarefa>) query.getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public void remover(Tarefa tarefa) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT e FROM " + tarefa.getClass().getName() + " e ");
		sql.append(" WHERE e.nuTarefa = :id ");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("id", tarefa.getNuTarefa());
			if (!query.getResultList().isEmpty()) {
				sql = new StringBuilder("");
				sql.append(" DELETE FROM " + tarefa.getClass().getName() + " e");
				sql.append(" WHERE e.nuTarefa = " + tarefa.getNuTarefa());
				query = em.createQuery(sql.toString());
				query.executeUpdate();
			}
		} catch (NoResultException e) {
			LOGGER.info(e);
		}

	}
	
}
