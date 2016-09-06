package br.com.agenda.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.entity.Contato;

@Named
@Stateless
public class ContatoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ContatoDAO contatoDAO;

	public void inserir(Contato contato) {
		contato.setNuTelefone(
				contato.getNuTelefone().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
		contatoDAO.inserir(contato);
	}

	public List<String> buscarContatoPorNome(Contato contato) {
		return contatoDAO.buscarContatoPorNome(contato);
	}

	public List<String> buscarContatoPorTelefone(Contato contato) {
		contato.setNuTelefone(
				contato.getNuTelefone().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
		return contatoDAO.buscarContatoPorTelefone(contato);
	}
}
