package br.com.agenda.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Telefone;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class TelefoneDAO extends DAO<Telefone> {

	private static final Logger LOGGER = Logger.getLogger(DAO.class);

	/**
	 * 
	 * @param numero
	 * @return List<Contato>
	 */
	@SuppressWarnings("unchecked")
	public List<Telefone> buscarTelefonePorNumero(String numero) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT c FROM Telefone c ");
		sql.append(" WHERE LOWER(c.nuTelefone) = LOWER(:numero)");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("numero", numero);
			return (List<Telefone>) query.getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return Collections.emptyList();
		}
	}

	/**
	 * 
	 * @param telefone
	 * @return
	 */
	public Boolean verificaTelefoneExistente(Telefone telefone) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT t FROM Telefone t ");
		sql.append(" WHERE t.nuTelefone =:nuTelefone ");
		if (null != telefone.getIdTelefone()) {
			sql.append(" AND t.idTelefone <> :id_telefone ");
		}
		Query query = em.createQuery(sql.toString());
		query.setParameter("nuTelefone", telefone.getNuTelefone());
		if (null != telefone.getIdTelefone()) {
			query.setParameter("id_telefone", telefone.getIdTelefone());
		}
		query.setMaxResults(1);
		try {
			query.getSingleResult();
			return Boolean.TRUE;
		} catch (NoResultException e) {
			return Boolean.FALSE;
		}

	}

}
