package br.com.agenda.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.agenda.dao.LembreteDAO;
import br.com.agenda.entity.Lembrete;

/**
 * 
 * @author bruno.calmon
 *
 */
@Stateless
public class LembreteService {

	@EJB
	private LembreteDAO lembreteDAO;

	/**
	 * 
	 * @param lembrete
	 */
	public void inserir(Lembrete lembrete) {
		lembreteDAO.inserir(lembrete);
	}
}
