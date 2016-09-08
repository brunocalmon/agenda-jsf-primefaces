package br.com.agenda.validacoes;

import br.com.agenda.entity.Contato;
import br.com.agenda.entity.Lembrete;
import br.com.agenda.exceptions.AgendaException;

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
		if (nullOrEmpty(contato.getNuTelefone())) {
			throw new AgendaException("Campo telefone não pode estar vazio");
		}
	}
}
