package br.com.agenda.mb;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Lembrete;
import br.com.agenda.enums.PageEnum;
import br.com.agenda.exceptions.AgendaException;
import br.com.agenda.service.LembreteService;
import br.com.agenda.util.StringUtil;
import br.com.agenda.validacoes.ValidacoesLembrete;
import br.com.agenda.visao.LembreteVisao;

/**
 * 
 * @author bruno.calmon
 *
 */
@ViewScoped
@ManagedBean()
public class LembreteMB extends GenericMB {

	private static final long serialVersionUID = 1674348933662083014L;

	private static final Logger LOGGER = Logger.getLogger(Lembrete.class);
	private static final String LEMBRETE_VISAO = "lembreteVisao";

	@EJB
	private LembreteService lembreteService;
	private LembreteVisao lembreteVisao;

	/**
	 * Inicializador
	 */
	@PostConstruct
	public void init() {
		this.setLembreteVisao((LembreteVisao) super.getFlash(LEMBRETE_VISAO));

		if (ValidacoesLembrete.nullOrEmpty(this.getLembreteVisao())) {
			this.setLembreteVisao(new LembreteVisao());
		} else {
			super.setFlash(LEMBRETE_VISAO, this.getLembreteVisao());
		}
		if (ValidacoesLembrete.nullOrEmpty(this.getLembreteVisao().getLembrete())) {
			this.getLembreteVisao().setLembrete(new Lembrete());

			this.getLembreteVisao()
					.setListaResultadoLembrete(lembreteService.buscarTodasLembretes(this.getLembreteVisao().getLembrete()));
		}
	}

	/**
	 * Persiste dados
	 */
	public void incluirLembrete() {
		this.getLembreteVisao().getLembrete()
				.setNoLembrete(StringUtil.limpaEspacosVazios(this.getLembreteVisao().getLembrete().getNoLembrete()));
		this.getLembreteVisao().getLembrete()
				.setDeLembrete(StringUtil.limpaEspacosVazios(this.getLembreteVisao().getLembrete().getDeLembrete()));

		this.getLembreteVisao().getLembrete().setDtLembrete(new Date());

		try {
			ValidacoesLembrete.validaInclusaoLembrete(this.getLembreteVisao().getLembrete());
			lembreteService.inserir(this.getLembreteVisao().getLembrete());
			exibirMsgSucesso("Lembrete " + this.getLembreteVisao().getLembrete().getNoLembrete() + " adicionada com sucesso!");

		} catch (AgendaException ae) {
			exibirMsgErro(ae.getMessage());
			LOGGER.info(ae);
		} catch (Exception e) {
			LOGGER.info(e);
		} finally {
			this.getLembreteVisao().setLembrete(new Lembrete());
		}

	}

	/**
	 * Faz consulta
	 */
	public void consultaLembrete() {
		this.getLembreteVisao()
				.setListaResultadoLembrete(lembreteService.buscarLembretePorNome(getLembreteVisao().getLembrete()));
	}

	/**
	 * 
	 * @param lembrete
	 * @return PAGE_CONSULTA_LEMBRETE
	 */
	public String editarLembrete() {
		try {
			ValidacoesLembrete.validaEdicaoLembrete(this.getLembreteVisao().getLembrete());
			lembreteService.atualizar(this.getLembreteVisao().getLembrete());
			super.exibirMsgSucesso(
					"Lembrete " + this.getLembreteVisao().getLembrete().getNoLembrete() + " atualizado com sucesso!");
		} catch (AgendaException ae) {
			super.exibirMsgErro(ae.getMessage());
			LOGGER.info(ae);
		}
		return this.redirecionaParaConsultarLembrete();
	}
	
	/**
	 * Exclui lembrete
	 */
	public void excluirLembrete(Lembrete lembrete) {
		try {
			lembreteService.remover(lembrete);
			init();
		} catch (Exception e) {
			super.exibirMsgErro("Ops! Por algum motivo, não conseguimos remover este lembrete. =(");
		}
	}
	
	/**
	 * 
	 * @param lembreteVisao
	 * @return String
	 */
	public String redirecionaParaEditarLembrete(Lembrete lembrete) {
		this.getLembreteVisao().setLembrete(lembrete);
		super.setFlash(LEMBRETE_VISAO, this.getLembreteVisao());
		return PageEnum.PAGE_EDITA_LEMBRETE.getValor();
	}

	/**
	 * 
	 * @param lembreteVisao
	 * @return String
	 */
	public String redirecionaParaIncluirLembrete() {
		return PageEnum.PAGE_INCLUI_LEMBRETE.getValor();
	}

	/**
	 * 
	 * @param lembreteVisao
	 * @return String
	 */
	public String redirecionaParaConsultarLembrete() {
		return PageEnum.PAGE_CONSULTA_LEMBRETE.getValor();
	}
	
	/**
	 * <p>
	 * Limpa filtro
	 * </p>
	 */
	public void limpaFiltroLembrete() {
		this.getLembreteVisao().setLembrete(new Lembrete());
	}
	
	public LembreteVisao getLembreteVisao() {
		return lembreteVisao;
	}

	public void setLembreteVisao(LembreteVisao lembreteVisao) {
		this.lembreteVisao = lembreteVisao;
	}
}
