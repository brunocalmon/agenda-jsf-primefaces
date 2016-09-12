package br.com.agenda.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * 
 * @author bruno.calmon
 *
 */
public class GenericMB implements Serializable {

	private static final long serialVersionUID = -2948837851449626084L;

	protected void exibirMsgSucesso(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	protected void exibirMsgErro(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	protected Object getFlash(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(key);
	}

	protected void setFlash(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, value);
	}
}
