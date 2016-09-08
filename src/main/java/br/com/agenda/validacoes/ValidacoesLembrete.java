package br.com.agenda.validacoes;

import br.com.agenda.entity.Lembrete;
import br.com.agenda.exceptions.AgendaException;

/**
 * 
 * @author bruno.calmon
 *
 */
public class ValidacoesLembrete extends Validacoes {
	
	/**
	 * 
	 * @param lembrete
	 * @throws AgendaException
	 */
	public static void validaInclusaoLembrete(Lembrete lembrete) throws AgendaException {
		if (nullOrEmpty(lembrete.getNoLembrete())) {
			throw new AgendaException("Campo não pode ser vazio.");
		}
		if (lembrete.getNoLembrete().length() < DEZ) {
			throw new AgendaException("Nome muito curto, precisa ser mais que 10");
		}
		if (lembrete.getDeLembrete().length() < CINQUENTA) {
			throw new AgendaException("Nome muito curto, precisa ser mais que 10");
		}
	}
	
}
