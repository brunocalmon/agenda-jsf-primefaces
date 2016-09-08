package br.com.agenda.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.entity.Contato;
import br.com.agenda.util.StringUtil;

@Named
@Stateless
public class ContatoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ContatoDAO contatoDAO;

	public void inserir(Contato contato) {
		contato.setNuTelefone(StringUtil.desformatString("(##) ####-####", contato.getNuTelefone()));
		contatoDAO.inserir(contato);
	}

	public List<Contato> buscarContatoPorNome(Contato contato) {
		return contatoDAO.buscarContatoPorNome(contato);
	}

	public List<Contato> buscarContatoPorTelefone(Contato contato) {
		contato.setNuTelefone(
				contato.getNuTelefone().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
		return contatoDAO.buscarContatoPorTelefone(contato);
	}
}
