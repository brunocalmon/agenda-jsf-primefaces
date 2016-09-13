package br.com.agenda.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.dao.TelefoneDAO;
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
	@EJB
	private TelefoneDAO telefoneDAO;

	/**
	 * 
	 * @param contato
	 */
	public void inserir(Contato contato) {
		contatoDAO.inserir(contato);
//		for (ContatoTelefone ct : contato.getListaTelefone()) {
//			telefoneDAO.inserir(ct.getPk().getTelefone());
//			ct.getPk().setContato(contato);
//		}
//		contatoDAO.inserirTelefones(contato.getListaTelefone());
	}

	/**
	 * 
	 * @param contato
	 */
	public void atualizar(Contato contato) {
		// contato.setNuTelefone(StringUtil.desformatString("(##) ####-####",
		// contato.getNuTelefone()));
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
		// contato.setNuTelefone(
		// contato.getNuTelefone().replace("(", "").replace(")",
		// "").replace("-", "").replace(" ", ""));
		return contatoDAO.buscarContatoPorTelefone(nuTelefone);
		// return null;
	}

	/**
	 * 
	 * @param contato
	 * @return List<Contato> 
	 */
	public List<Contato> buscarTodosContatos(Contato contato) {
		return contatoDAO.buscarTodos(contato);
	}

}
