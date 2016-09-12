package br.com.agenda.enums;

public enum PageEnum {
	PAGE_HOME("/pages/home/home.xhtml"), PAGE_EDITA_CONTATO(
			"/pages/contato/editarContato.xhtml"), PAGE_CONSULTA_CONTATO(
					"/pages/contato/consultaContato.jsf"), PAGE_INCLUI_CONTATO(
							"/pages/contato/incluirContato.jsf"), PAGE_EDITA_TAREFA(
									"/pages/tarefa/editarTarefa.xhtml"), PAGE_CONSULTA_TAREFA(
											"/pages/tarefa/consultaTarefa.jsf"), PAGE_INCLUI_TAREFA(
													"/pages/tarefa/incluirTarefa.jsf"), PAGE_EDITA_LEMBRETE(
															"/pages/lembrete/editarLembrete.xhtml"), PAGE_CONSULTA_LEMBRETE(
																	"/pages/lembrete/consultaLembrete.jsf"), PAGE_INCLUI_LEMBRETE(
																			"/pages/lembrete/incluirLembrete.jsf");

	private String value;

	private PageEnum(String value) {
		this.value = value;
	}

	public String getValor() {
		return value;
	}
}
