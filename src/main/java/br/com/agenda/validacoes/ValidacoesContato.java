package br.com.agenda.validacoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.agenda.entity.Contato;
import br.com.agenda.entity.Telefone;
import br.com.agenda.exceptions.AgendaException;
import br.com.agenda.service.TelefoneService;
import br.com.agenda.util.StringUtil;
import br.com.agenda.visao.ContatoVisao;

/**
 * 
 * @author bruno.calmon
 *
 */
public abstract class ValidacoesContato extends Validacoes {

	/**
	 * 
	 * @param contato
	 * @throws AgendaException
	 */
	public static void validaInclusaoContato(Contato contato) throws AgendaException {
		if (contato.getNoContato().length() < TRES) {
			throw new AgendaException("Nome deve ter mais de 3 caracteres.");
		}
		if (contato.getNoContato().substring(0, 1).matches("[\\s\\W_\\d]")) {
			throw new AgendaException("Nome deve começar com uma letra.");
		}
		if (nullOrEmpty(contato.getNoContato())) {
			throw new AgendaException("Campo nome não pode estar vazio");
		}
		if (nullOrEmpty(contato.getListaTelefone())) {
			throw new AgendaException("Insira pelo menos um telefone");
		}
	}

	/**
	 * 
	 * @param contato
	 * @throws AgendaException
	 */
	public static void validaEdicaoContato(Contato contato) throws AgendaException {
		validaInclusaoContato(contato);

		if (nullOrEmpty(contato.getIdContato())) {
			throw new AgendaException("Houve uma falha ao tentar atualizar o contato " + contato.getNoContato()
					+ ". Contate o suporte ou tente novamente mais tarde.");
		}
		if (nullOrEmpty(contato.getDtEntrada())) {
			throw new AgendaException("Houve uma falha ao tentar atualizar o contato " + contato.getNoContato()
					+ ". Contate o suporte ou tente novamente mais tarde.");
		}
	}

	/**
	 * 
	 * @param listaTelefone
	 * @param telefoneService
	 * @return bool
	 */
	public static boolean verificaTelefoneDuplicadoNaBase(Telefone telefone, TelefoneService telefoneService) {
		return telefoneService.verificaTelefoneExistente(telefone);
	}

	/**
	 * 
	 * @param listaTelefone
	 * @return
	 */
	public static boolean verificaTelefonesDuplicadosEmTela(List<Telefone> listaTelefone) {
		Map<String, Integer> contador = new HashMap<String, Integer>();
		for (Telefone valor : listaTelefone) {
			if (!contador.containsKey(valor.getNuTelefone())) {
				contador.put(valor.getNuTelefone(), 0);
			}
			contador.put(valor.getNuTelefone(), contador.get(valor.getNuTelefone()) + 1);
		}

		// Retorna true se houver mais de um.
		for (Map.Entry<String, Integer> item : contador.entrySet()) {
			if (item.getValue() >= 2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param contatoVisao
	 * @param telefoneService
	 * @return ContatoVisao
	 * @throws AgendaException
	 */
	public static ContatoVisao processarTelefones(ContatoVisao contatoVisao, TelefoneService telefoneService)
			throws AgendaException {
		Boolean isRepetidoBanco = Boolean.FALSE;
		Boolean isRepetidoTela;

		List<Telefone> listTel = contatoVisao.getContato().getListaTelefone();
		List<Telefone> auxTel = new ArrayList<Telefone>();
		for (Telefone t : listTel) {
			t.setNuTelefone(StringUtil.desformatString("(##) ####-####", t.getNuTelefone()));
			auxTel.add(t);
		}
		contatoVisao.getContato().setListaTelefone(auxTel);

		isRepetidoTela = ValidacoesContato.verificaTelefonesDuplicadosEmTela(listTel);

		for (Telefone telefone : listTel) {
			isRepetidoBanco = ValidacoesContato.verificaTelefoneDuplicadoNaBase(telefone, telefoneService);
			if (isRepetidoBanco) {
				break;
			}
		}

		if (isRepetidoTela || isRepetidoBanco) {
			throw new AgendaException("Telefone já cadastrado.");
		}
		return contatoVisao;
	}
}
