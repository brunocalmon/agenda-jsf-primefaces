package br.com.agenda.enums;

public enum PageEnum {
	PAGE_HOME("/pages/home/home.xhtml"), PAGE_EDITA_CONTATO(
			"/pages/contato/editarContato.xhtml"), PAGE_CONSULTA_CONTATO(
					"/pages/contato/consultaContato.jsf"), PAGE_INCLUI_CONTATO("/pages/contato/incluirContato.jsf");

	private String value;

	private PageEnum(String value) {
		this.value = value;
	}

	public String getValor() {
		return value;
	}
}
