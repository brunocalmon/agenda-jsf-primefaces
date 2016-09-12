package br.com.agenda.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.dao.TelefoneDAO;
import br.com.agenda.entity.Telefone;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class TelefoneService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private TelefoneDAO telefoneDAO;

	/**
	 * 
	 * @param telefone
	 * @return List<Contato>
	 */
	public List<Telefone> buscarTodosTelefone(Telefone telefone) {
		return telefoneDAO.buscarTodos(telefone);
	}

}
