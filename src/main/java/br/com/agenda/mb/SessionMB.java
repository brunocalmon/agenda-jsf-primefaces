package br.com.agenda.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class SessionMB {

	String tituloTela;

	@PostConstruct
	public void init() {

		this.tituloTela = "Bem-Vindo";

	}

	public String getTituloTela() {
		return tituloTela;
	}

	public void setTituloTela(String tituloTela) {
		this.tituloTela = tituloTela;
	}

}
