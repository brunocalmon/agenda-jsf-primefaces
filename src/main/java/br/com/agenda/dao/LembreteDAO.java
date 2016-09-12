package br.com.agenda.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Lembrete;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class LembreteDAO extends DAO<Lembrete> {
	
	private static final Logger LOGGER = Logger.getLogger(DAO.class);

	/**
	 * 
	 * @param lembrete
	 * @return List<Lembrete>
	 */
	@SuppressWarnings("unchecked")
	public List<Lembrete> buscarLembretePorNome(Lembrete lembrete) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT c FROM Lembrete c ");
		sql.append(" WHERE LOWER(c.noLembrete) LIKE LOWER(CONCAT('%', :nome, '%')))");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("nome", lembrete.getNoLembrete());
			return (List<Lembrete>) query.getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public void remover(Lembrete lembrete) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT e FROM " + lembrete.getClass().getName() + " e ");
		sql.append(" WHERE e.nuLembrete = :id ");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("id", lembrete.getNuLembrete());
			if (!query.getResultList().isEmpty()) {
				sql = new StringBuilder("");
				sql.append(" DELETE FROM " + lembrete.getClass().getName() + " e");
				sql.append(" WHERE e.nuLembrete = " + lembrete.getNuLembrete());
				query = em.createQuery(sql.toString());
				query.executeUpdate();
			}
		} catch (NoResultException e) {
			LOGGER.info(e);
		}

	}
	
}
