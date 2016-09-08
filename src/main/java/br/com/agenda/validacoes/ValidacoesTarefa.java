package br.com.agenda.validacoes;

import br.com.agenda.entity.Tarefa;
import br.com.agenda.exceptions.AgendaException;

public class ValidacoesTarefa extends Validacoes {

	public static void validaInclusaoTarefa(Tarefa tarefa) throws AgendaException {
		if (nullOrEmpty(tarefa.getNoTarefa())) {
			throw new AgendaException("Campo não poed ser vazio.");
		}
		if (tarefa.getNoTarefa().length() < DEZ) {
			throw new AgendaException("Nome da tarefa muito curta, precisa ser maior que 10");
		}
		if (tarefa.getDeTarefa().length() < CINQUENTA) {
			throw new AgendaException("Descrição de tarefa muito curta, precisa ser maior que 10");
		}
	}
	
}
