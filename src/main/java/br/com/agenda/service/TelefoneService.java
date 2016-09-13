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
	public List<Telefone> buscarTodosTelefone() {
		return telefoneDAO.buscarTodos();
	}
	
	/**
	 * 
	 * @param telefone
	 */
	public void remover(Telefone telefone) {
		telefoneDAO.remover(telefone);
	}

	/**
	 * 
	 * @param telefone
	 */
	public Boolean verificaTelefoneExistente(Telefone telefone) {
		return telefoneDAO.verificaTelefoneExistente(telefone);
	}
}
