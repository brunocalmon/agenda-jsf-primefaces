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
		if (lembrete.getNoLembrete().length() < TRES) {
			throw new AgendaException("Nome da lembrete muito curta, precisa ser maior que 3");
		}
		if (lembrete.getDeLembrete().length() < DEZ) {
			throw new AgendaException("Descrição de lembrete muito curta, precisa ser maior que 10");
		}
	}
	
	/**
	 * 
	 * @param contato
	 * @throws AgendaException
	 */
	public static void validaEdicaoLembrete(Lembrete lembrete) throws AgendaException {
		validaInclusaoLembrete(lembrete);

		if (nullOrEmpty(lembrete.getNuLembrete())) {
			throw new AgendaException("Houve uma falha ao tentar atualizar a lembrete " + lembrete.getNoLembrete()
					+ ". Contate o suporte ou tente novamente mais tarde.");
		}
		if (nullOrEmpty(lembrete.getDtLembrete())) {
			throw new AgendaException("Houve uma falha ao tentar atualizar o lembrete " + lembrete.getNoLembrete()
					+ ". Contate o suporte ou tente novamente mais tarde.");
		}
	}
}
