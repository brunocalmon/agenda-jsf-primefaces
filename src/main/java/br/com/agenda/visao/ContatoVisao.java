package br.com.agenda.visao;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.agenda.entity.Contato;

public class ContatoVisao {

	private Contato contato;
	private List<SelectItem> tipoBuscaContato;
	private Integer tipoBuscaContatoSelecionado;
	private List<String> listaResultadoContato;

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<SelectItem> getTipoBuscaContato() {
		return tipoBuscaContato;
	}

	public void setTipoBuscaContato(List<SelectItem> tipoBuscaContato) {
		this.tipoBuscaContato = tipoBuscaContato;
	}

	public Integer getTipoBuscaContatoSelecionado() {
		return tipoBuscaContatoSelecionado;
	}

	public void setTipoBuscaContatoSelecionado(Integer tipoBuscaContatoSelecionado) {
		this.tipoBuscaContatoSelecionado = tipoBuscaContatoSelecionado;
	}

	public List<String> getListaResultadoContato() {
		return listaResultadoContato;
	}

	public void setListaResultadoContato(List<String> listaResultadoContato) {
		this.listaResultadoContato = listaResultadoContato;
	}
}
