package br.com.agenda.mb;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Tarefa;
import br.com.agenda.exceptions.AgendaException;
import br.com.agenda.service.TarefaService;
import br.com.agenda.util.StringUtil;
import br.com.agenda.validacoes.ValidacoesTarefa;

/**
 * 
 * @author bruno.calmon
 *
 */
@ViewScoped
@ManagedBean()
public class TarefaMB extends GenericMB {

	private static final long serialVersionUID = 1674348933662083014L;
	
	private static final Logger LOGGER = Logger.getLogger(TarefaMB.class);

	private Tarefa tarefa;

	@EJB
	private TarefaService tarefaService;
	
	@PostConstruct
	private void init() {
		if (ValidacoesTarefa.nullOrEmpty(getTarefa())) {
			setTarefa(new Tarefa());
		}
	}

	/**
	 * Persiste dados
	 */
	public void incluirTarefa() {
		getTarefa().setNoTarefa(StringUtil.limpaEspacosVazios(getTarefa().getNoTarefa()));
		getTarefa().setDeTarefa(StringUtil.limpaEspacosVazios(getTarefa().getDeTarefa()));

		getTarefa().setDtTarefa(new Date());

		try {
			ValidacoesTarefa.validaInclusaoTarefa(getTarefa());
			tarefaService.inserir(getTarefa());
			exibirMsgSucesso("Tarefa " + getTarefa().getNoTarefa() + " adicionada com sucesso!");

		} catch (AgendaException ae) {
			exibirMsgErro(ae.getMessage());
			LOGGER.info(ae);
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
