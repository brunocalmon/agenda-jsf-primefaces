package br.com.agenda.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.dao.TarefaDAO;
import br.com.agenda.entity.Tarefa;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class TarefaService implements Serializable {

	private static final long serialVersionUID = -4823677408165938330L;

	@EJB
	private TarefaDAO tarefaDAO;

	/**
	 * 
	 * @param tarefa
	 */
	public void inserir(Tarefa tarefa) {
		tarefaDAO.inserir(tarefa);
	}

	/**
	 * 
	 * @param tarefa
	 */
	public void atualizar(Tarefa tarefa) {
		tarefaDAO.atualizar(tarefa);
	}
	
	/**
	 * 
	 * @param tarefa
	 */
	public void remover(Tarefa tarefa) {
		tarefaDAO.remover(tarefa);
	}
	
	/**
	 * 
	 * @param tarefa
	 * @return List<Tarefa>
	 */
	public List<Tarefa> buscarTodasTarefas(Tarefa tarefa) {
		return tarefaDAO.buscarTodos(tarefa);
	}
	
	/**
	 * 
	 * @param tarefa
	 * @return List<Tarefa>
	 */
	public List<Tarefa> buscarTarefaPorNome(Tarefa tarefa) {
		return tarefaDAO.buscarTarefaPorNome(tarefa);
	}
}
