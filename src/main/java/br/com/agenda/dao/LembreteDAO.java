package br.com.agenda.dao;

import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.entity.Lembrete;

/**
 * 
 * @author bruno.calmon
 *
 */
@Named
@Stateless
public class LembreteDAO extends DAO<Lembrete>{
}
