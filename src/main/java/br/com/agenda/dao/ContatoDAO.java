package br.com.agenda.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Table;

import br.com.agenda.entity.Contato;

@Named
@Stateless
@Table(name = "contato", schema = "public")
public class ContatoDAO extends DAO<Contato> {

	@SuppressWarnings("unchecked")
	public List<String> buscarContatoPorNome(Contato contato) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT e FROM contato e WHERE e.no_contato = " + contato.getNoContato());
		try {
			Query query = em.createQuery(sql.toString());
//			query.setParameter("contato", Contato.class);
//			query.setParameter("noContato", contato.getNoContato());
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> buscarContatoPorTelefone(Contato contato) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT c.no_contato, c.nu_telefone, c.dt_entrada FROM :Contato c ");
		sql.append(" WHERE c.nu_telefone LIKE :nuTelefone");
		try {
			Query query = em.createQuery(sql.toString());
			query.setParameter("Contato", contato);
			query.setParameter(":nuTelefone", "%" + contato.getNuTelefone() + "%");
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
