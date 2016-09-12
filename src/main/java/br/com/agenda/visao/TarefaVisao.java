package br.com.agenda.visao;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.agenda.entity.Tarefa;

/**
 * 
 * @author bruno.calmon
 *
 */
public class TarefaVisao {

	private Tarefa tarefa;
	private List<SelectItem> tipoBuscaTarefa;
	private Integer tipoBuscaTarefaSelecionado;
	private List<Tarefa> listaResultadoTarefa;

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<SelectItem> getTipoBuscaTarefa() {
		return tipoBuscaTarefa;
	}

	public void setTipoBuscaTarefa(List<SelectItem> tipoBuscaTarefa) {
		this.tipoBuscaTarefa = tipoBuscaTarefa;
	}

	public Integer getTipoBuscaTarefaSelecionado() {
		return tipoBuscaTarefaSelecionado;
	}

	public void setTipoBuscaTarefaSelecionado(Integer tipoBuscaTarefaSelecionado) {
		this.tipoBuscaTarefaSelecionado = tipoBuscaTarefaSelecionado;
	}

	public List<Tarefa> getListaResultadoTarefa() {
		return listaResultadoTarefa;
	}

	public void setListaResultadoTarefa(List<Tarefa> listaResultadoTarefa) {
		this.listaResultadoTarefa = listaResultadoTarefa;
	}
}
