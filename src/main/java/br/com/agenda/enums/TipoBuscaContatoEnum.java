package br.com.agenda.enums;

/**
 * 
 * @author bruno.calmon
 *
 */
public enum TipoBuscaContatoEnum {
	NOME(1, "Nome"), TELEFONE(2, "Telefone");

	private Integer id;
	private String valor;
	
	private TipoBuscaContatoEnum(Integer id, String valor) {
		this.id = id;
		this.valor = valor;
	}
	
	public Integer getId() {
		return id;
	}

	public String getValor() {
		return valor;
	}
}
