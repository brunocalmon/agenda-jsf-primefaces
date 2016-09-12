package br.com.agenda.mb;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Tarefa;
import br.com.agenda.enums.PageEnum;
import br.com.agenda.exceptions.AgendaException;
import br.com.agenda.service.TarefaService;
import br.com.agenda.util.StringUtil;
import br.com.agenda.validacoes.ValidacoesTarefa;
import br.com.agenda.visao.TarefaVisao;

/**
 * 
 * @author bruno.calmon
 *
 */
@ViewScoped
@ManagedBean()
public class TarefaMB extends GenericMB {

	private static final long serialVersionUID = 1674348933662083014L;

	private static final Logger LOGGER = Logger.getLogger(Tarefa.class);
	private static final String TAREFA_VISAO = "tarefaVisao";

	@EJB
	private TarefaService tarefaService;
	private TarefaVisao tarefaVisao;

	/**
	 * Inicializador
	 */
	@PostConstruct
	public void init() {
		this.setTarefaVisao((TarefaVisao) super.getFlash(TAREFA_VISAO));

		if (ValidacoesTarefa.nullOrEmpty(this.getTarefaVisao())) {
			this.setTarefaVisao(new TarefaVisao());
		} else {
			super.setFlash(TAREFA_VISAO, this.getTarefaVisao());
		}
		if (ValidacoesTarefa.nullOrEmpty(this.getTarefaVisao().getTarefa())) {
			this.getTarefaVisao().setTarefa(new Tarefa());

			this.getTarefaVisao()
					.setListaResultadoTarefa(tarefaService.buscarTodasTarefas(this.getTarefaVisao().getTarefa()));
		}
	}

	/**
	 * Persiste dados
	 */
	public void incluirTarefa() {
		this.getTarefaVisao().getTarefa()
				.setNoTarefa(StringUtil.limpaEspacosVazios(this.getTarefaVisao().getTarefa().getNoTarefa()));
		this.getTarefaVisao().getTarefa()
				.setDeTarefa(StringUtil.limpaEspacosVazios(this.getTarefaVisao().getTarefa().getDeTarefa()));

		this.getTarefaVisao().getTarefa().setDtTarefa(new Date());

		try {
			ValidacoesTarefa.validaInclusaoTarefa(this.getTarefaVisao().getTarefa());
			tarefaService.inserir(this.getTarefaVisao().getTarefa());
			exibirMsgSucesso("Tarefa " + this.getTarefaVisao().getTarefa().getNoTarefa() + " adicionada com sucesso!");

		} catch (AgendaException ae) {
			exibirMsgErro(ae.getMessage());
			LOGGER.info(ae);
		} catch (Exception e) {
			LOGGER.info(e);
		} finally {
			this.getTarefaVisao().setTarefa(new Tarefa());
		}

	}

	/**
	 * Faz consulta
	 */
	public void consultaTarefa() {
		this.getTarefaVisao()
				.setListaResultadoTarefa(tarefaService.buscarTarefaPorNome(getTarefaVisao().getTarefa()));
	}

	/**
	 * 
	 * @param tarefa
	 * @return PAGE_CONSULTA_TAREFA
	 */
	public String editarTarefa() {
		try {
			ValidacoesTarefa.validaEdicaoTarefa(this.getTarefaVisao().getTarefa());
			tarefaService.atualizar(this.getTarefaVisao().getTarefa());
			super.exibirMsgSucesso(
					"Tarefa " + this.getTarefaVisao().getTarefa().getNoTarefa() + " atualizado com sucesso!");
		} catch (AgendaException ae) {
			super.exibirMsgErro(ae.getMessage());
			LOGGER.info(ae);
		}
		return this.redirecionaParaConsultarTarefa();
	}
	
	/**
	 * Exclui tarefa
	 */
	public void excluirTarefa(Tarefa tarefa) {
		try {
			tarefaService.remover(tarefa);
			init();
		} catch (Exception e) {
			super.exibirMsgErro("Ops! Por algum motivo, não conseguimos remover este tarefa. =(");
		}
	}
	
	/**
	 * 
	 * @param tarefaVisao
	 * @return String
	 */
	public String redirecionaParaEditarTarefa(Tarefa tarefa) {
		this.getTarefaVisao().setTarefa(tarefa);
		super.setFlash(TAREFA_VISAO, this.getTarefaVisao());
		return PageEnum.PAGE_EDITA_TAREFA.getValor();
	}

	/**
	 * 
	 * @param tarefaVisao
	 * @return String
	 */
	public String redirecionaParaIncluirTarefa() {
		return PageEnum.PAGE_INCLUI_TAREFA.getValor();
	}

	/**
	 * 
	 * @param tarefaVisao
	 * @return String
	 */
	public String redirecionaParaConsultarTarefa() {
		return PageEnum.PAGE_CONSULTA_TAREFA.getValor();
	}
	
	/**
	 * <p>
	 * Limpa filtro
	 * </p>
	 */
	public void limpaFiltroTarefa() {
		this.getTarefaVisao().setTarefa(new Tarefa());
	}
	
	public TarefaVisao getTarefaVisao() {
		return tarefaVisao;
	}

	public void setTarefaVisao(TarefaVisao tarefaVisao) {
		this.tarefaVisao = tarefaVisao;
	}
}
