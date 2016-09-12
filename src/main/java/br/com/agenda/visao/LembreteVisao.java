package br.com.agenda.visao;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.agenda.entity.Lembrete;

/**
 * 
 * @author bruno.calmon
 *
 */
public class LembreteVisao {

	private Lembrete lembrete;
	private List<SelectItem> tipoBuscaLembrete;
	private Integer tipoBuscaLembreteSelecionado;
	private List<Lembrete> listaResultadoLembrete;

	public Lembrete getLembrete() {
		return lembrete;
	}

	public void setLembrete(Lembrete lembrete) {
		this.lembrete = lembrete;
	}

	public List<SelectItem> getTipoBuscaLembrete() {
		return tipoBuscaLembrete;
	}

	public void setTipoBuscaLembrete(List<SelectItem> tipoBuscaLembrete) {
		this.tipoBuscaLembrete = tipoBuscaLembrete;
	}

	public Integer getTipoBuscaLembreteSelecionado() {
		return tipoBuscaLembreteSelecionado;
	}

	public void setTipoBuscaLembreteSelecionado(Integer tipoBuscaLembreteSelecionado) {
		this.tipoBuscaLembreteSelecionado = tipoBuscaLembreteSelecionado;
	}

	public List<Lembrete> getListaResultadoLembrete() {
		return listaResultadoLembrete;
	}

	public void setListaResultadoLembrete(List<Lembrete> listaResultadoLembrete) {
		this.listaResultadoLembrete = listaResultadoLembrete;
	}
}
