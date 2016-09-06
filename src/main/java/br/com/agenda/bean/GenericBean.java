package br.com.agenda.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GenericBean implements Serializable {

	private static final long serialVersionUID = -2948837851449626084L;

	protected void exibirMsgSucesso(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	protected void exibirMsgErro(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	protected String limpaEspacosVazios(String valor) {
		return valor.trim();
	}
}
