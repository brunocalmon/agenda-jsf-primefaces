package br.com.agenda.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Tarefa;
import br.com.agenda.exceptions.AgendaException;
import br.com.agenda.service.TarefaService;
import br.com.agenda.validacoes.ValidacoesTarefa;

@ViewScoped
@ManagedBean(name = "tarefaBean")
public class TarefaBean extends GenericBean {

	private static final long serialVersionUID = 1674348933662083014L;
	
	private static final Logger LOGGER = Logger.getLogger(TarefaBean.class);

	private Tarefa tarefa;

	@PostConstruct
	private void init() {
		if (ValidacoesTarefa.nullOrEmpty(getTarefa())) {
			setTarefa(new Tarefa());
		}
	}

	@EJB
	private TarefaService tarefaService;

	public void incluirTarefa() {
		getTarefa().setNoTarefa(limpaEspacosVazios(getTarefa().getNoTarefa()));
		getTarefa().setDeTarefa(limpaEspacosVazios(getTarefa().getDeTarefa()));

		getTarefa().setDtTarefa(new Date());

		try {
			ValidacoesTarefa.validaInclusaoTarefa(getTarefa());
			tarefaService.inserir(getTarefa());
			exibirMsgSucesso("Tarefa " + getTarefa().getNoTarefa() + " adicionada com sucesso!");

		} catch (AgendaException ae) {
			exibirMsgErro(ae.getMessage());
		} catch (Exception e) {
			LOGGER.info(e);
		} finally {
			setTarefa(new Tarefa());
		}

	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
}
