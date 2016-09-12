package br.com.agenda.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.dao.LembreteDAO;
import br.com.agenda.entity.Lembrete;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class LembreteService implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -9031585773453597966L;
	
	@EJB
	private LembreteDAO lembreteDAO;

	/**
	 * 
	 * @param lembrete
	 */
	public void inserir(Lembrete lembrete) {
		lembreteDAO.inserir(lembrete);
	}

	/**
	 * 
	 * @param lembrete
	 */
	public void atualizar(Lembrete lembrete) {
		lembreteDAO.atualizar(lembrete);
	}
	
	/**
	 * 
	 * @param lembrete
	 */
	public void remover(Lembrete lembrete) {
		lembreteDAO.remover(lembrete);
	}
	
	/**
	 * 
	 * @param lembrete
	 * @return List<Lembrete>
	 */
	public List<Lembrete> buscarTodasLembretes(Lembrete lembrete) {
		return lembreteDAO.buscarTodos(lembrete);
	}
	
	/**
	 * 
	 * @param lembrete
	 * @return List<Lembrete>
	 */
	public List<Lembrete> buscarLembretePorNome(Lembrete lembrete) {
		return lembreteDAO.buscarLembretePorNome(lembrete);
	}
}
