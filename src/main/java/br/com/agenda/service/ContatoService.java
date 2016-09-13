package br.com.agenda.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.entity.Contato;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class ContatoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ContatoDAO contatoDAO;

	/**
	 * 
	 * @param contato
	 */
	public void inserir(Contato contato) {
		contatoDAO.inserir(contato);
	}

	/**
	 * 
	 * @param contato
	 */
	public void atualizar(Contato contato) {
		contatoDAO.atualizar(contato);
	}

	/**
	 * 
	 * @param contato
	 */
	public void remover(Contato contato) {
		contatoDAO.remover(contato);
	}

	/**
	 * 
	 * @param nome
	 * @return List<Contato>
	 */
	public List<Contato> buscarContatoPorNome(String nome) {
		return contatoDAO.buscarContatoPorNome(nome);
	}

	/**
	 * 
	 * @param nuTelefone
	 * @return List<Contato>
	 */
	public Contato buscarContatoPorTelefone(String nuTelefone) {
		return contatoDAO.buscarContatoPorTelefone(nuTelefone);
	}

	/**
	 * 
	 * @param contato
	 * @return List<Contato>
	 */
	public List<Contato> buscarTodosContatosSemTelefone() {
		return contatoDAO.buscarTodos();
	}

	/**
	 * 
	 * @param contato
	 * @return List<Contato>
	 */
	public List<Contato> buscarTodosContatosComTelefones() {
		return contatoDAO.listaContatosComTelefones();
	}

}
