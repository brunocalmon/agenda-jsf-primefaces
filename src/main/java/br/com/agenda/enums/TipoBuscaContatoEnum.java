package br.com.agenda.enums;

public enum TipoBuscaContatoEnum {
	NOME(1, "nome"), TELEFONE(2, "telefone");
	
	private TipoBuscaContatoEnum(Integer id, String valor){
		this.id = id;
		this.valor = valor;
	}
	
	Integer id;
	String valor;
	
	public Integer getId(){
		return id;
	}
	
	public String getValor(){
		return valor;
	}
}
