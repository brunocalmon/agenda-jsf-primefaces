package br.com.agenda.validacoes;

import br.com.agenda.entity.Tarefa;
import br.com.agenda.exceptions.AgendaException;

/**
 * 
 * @author bruno.calmon
 *
 */
public class ValidacoesTarefa extends Validacoes {

	/**
	 * 
	 * @param tarefa
	 * @throws AgendaException
	 */
	public static void validaInclusaoTarefa(Tarefa tarefa) throws AgendaException {
		if (nullOrEmpty(tarefa.getNoTarefa())) {
			throw new AgendaException("Campo não pode ser vazio.");
		}
		if (tarefa.getNoTarefa().length() < TRES) {
			throw new AgendaException("Nome da tarefa muito curta, precisa ser maior que 3");
		}
		if (tarefa.getDeTarefa().length() < DEZ) {
			throw new AgendaException("Descrição de tarefa muito curta, precisa ser maior que 10");
		}
	}
	
	/**
	 * 
	 * @param contato
	 * @throws AgendaException
	 */
	public static void validaEdicaoTarefa(Tarefa tarefa) throws AgendaException {
		validaInclusaoTarefa(tarefa);

		if (nullOrEmpty(tarefa.getNuTarefa())) {
			throw new AgendaException("Houve uma falha ao tentar atualizar a tarefa " + tarefa.getNoTarefa()
					+ ". Contate o suporte ou tente novamente mais tarde.");
		}
		if (nullOrEmpty(tarefa.getDtTarefa())) {
			throw new AgendaException("Houve uma falha ao tentar atualizar o tarefa " + tarefa.getNoTarefa()
					+ ". Contate o suporte ou tente novamente mais tarde.");
		}
	}
}
