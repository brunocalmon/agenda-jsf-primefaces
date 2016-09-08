package br.com.agenda.dao;

import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.entity.Tarefa;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class TarefaDAO extends DAO<Tarefa> {
}
