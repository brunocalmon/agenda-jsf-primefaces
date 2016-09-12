package br.com.agenda.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.entity.Contato;
import br.com.agenda.util.StringUtil;

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
		contato.setNuTelefone(StringUtil.desformatString("(##) ####-####", contato.getNuTelefone()));
		contatoDAO.inserir(contato);
	}

	/**
	 * 
	 * @param contato
	 */
	public void atualizar(Contato contato) {
		contato.setNuTelefone(StringUtil.desformatString("(##) ####-####", contato.getNuTelefone()));
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
	 * @param contato
	 * @return List<Contato>
	 */
	public List<Contato> buscarContatoPorNome(Contato contato) {
		return contatoDAO.buscarContatoPorNome(contato);
	}

	/**
	 * 
	 * @param contato
	 * @return List<Contato>
	 */
	public List<Contato> buscarContatoPorTelefone(Contato contato) {
		contato.setNuTelefone(
				contato.getNuTelefone().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
		return contatoDAO.buscarContatoPorTelefone(contato);
	}
	
	public List<Contato> buscarTodosContatos(Contato contato) {
		return contatoDAO.buscarTodos(contato);
	}
	
}
