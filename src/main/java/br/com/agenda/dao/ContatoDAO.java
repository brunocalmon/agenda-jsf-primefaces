package br.com.agenda.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.agenda.entity.Contato;

@Named
@Stateless
public class ContatoDAO extends DAO<Contato> {

	@SuppressWarnings("unchecked")
	public List<Contato> buscarContatoPorNome(Contato contato) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT c FROM Contato c ");
		sql.append(" WHERE LOWER(c.noContato) LIKE LOWER(CONCAT('%', :nome, '%')))");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("nome", contato.getNoContato());
			return (List<Contato>) query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contato> buscarContatoPorTelefone(Contato contato) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT c FROM Contato c ");
		sql.append(" WHERE c.nuTelefone = :telefone ");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("telefone", contato.getNuTelefone());
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
