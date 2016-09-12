package br.com.agenda.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Contato;
import br.com.agenda.entity.ContatoTelefone;
import br.com.agenda.entity.Telefone;

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
		sql.append(" SELECT c FROM Contato c ");
		sql.append(" WHERE LOWER(c.noContato) LIKE LOWER(CONCAT('%', :nome, '%')))");
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
	 * @param nuTelefone
	 * @return String
	 */
	public Contato buscarContatoPorTelefone(String nuTelefone) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT ct.pk.contato FROM ContatoTelefone ct ");
		sql.append(" WHERE ct.pk.telefone.nuTelefone =:nuTelefone ");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("nuTelefone", nuTelefone);
			return (Contato) query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return null;
		}
	}

	@Override
	public void remover(Contato contato) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT e FROM " + contato.getClass().getName() + " e ");
		sql.append(" WHERE e.nuContato = :id ");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("id", contato.getNuContato());
			if (!query.getResultList().isEmpty()) {
				sql = new StringBuilder("");
				sql.append(" DELETE FROM " + contato.getClass().getName() + " e");
				sql.append(" WHERE e.nuContato = " + contato.getNuContato());
				query = em.createQuery(sql.toString());
				query.executeUpdate();
			}
		} catch (NoResultException e) {
			LOGGER.info(e);
		}
	}

	/**
	 * 
	 * @param lista
	 */
	public void inserirTelefones(List<ContatoTelefone> lista) {
		for (ContatoTelefone ct : lista) {
			ct.getPk().setContato(em.find(Contato.class, ct.getPk().getContato().getNuContato()));
			ct.getPk().setTelefone(em.find(Telefone.class, ct.getPk().getTelefone().getIdTelefone()));
			em.persist(ct);
		}
	}
}
