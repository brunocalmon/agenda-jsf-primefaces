package br.com.agenda.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Contato;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class ContatoDAO extends DAO<Contato> {

	private static final Logger LOGGER = Logger.getLogger(DAO.class);

	/**
	 * 
	 * @param nome
	 * @return List<Contato>
	 */
	@SuppressWarnings("unchecked")
	public List<Contato> buscarContatoPorNome(String nome) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT DISTINCT c FROM Contato c ");
		sql.append(" JOIN FETCH c.listaTelefone ");
		sql.append(" WHERE LOWER(c.noContato) LIKE LOWER(CONCAT('%', :nome, '%'))) ");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("nome", nome);
			return (List<Contato>) query.getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Faz busca por telefone
	 * 
	 * @param nuTelefone
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public List<Contato> buscarContatoPorTelefone(String nuTelefone) {
		StringBuilder sql = new StringBuilder("");

		sql.append(" SELECT DISTINCT t.contato FROM Telefone t ");
		sql.append(" JOIN FETCH t.contato.listaTelefone ");
		sql.append(" WHERE LOWER(t.nuTelefone) LIKE LOWER(CONCAT('%', :nuTelefone, '%'))) ");

		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("nuTelefone", nuTelefone);
			return (List<Contato>) query.getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return Collections.emptyList();
		}
	}

	/**
	 * 
	 * @param nome
	 * @return List<Contato>
	 */
	@SuppressWarnings("unchecked")
	public List<Contato> listaContatosComTelefones() {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT DISTINCT c FROM Contato c ");
		sql.append(" JOIN FETCH c.listaTelefone");
		try {
			Query query = em.createQuery(sql.toString());
			return (List<Contato>) query.getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return Collections.emptyList();
		}
	}
}
