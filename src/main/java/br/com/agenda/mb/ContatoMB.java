package br.com.agenda.mb;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Contato;
import br.com.agenda.enums.PageEnum;
import br.com.agenda.enums.TipoBuscaContatoEnum;
import br.com.agenda.exceptions.AgendaException;
import br.com.agenda.service.ContatoService;
import br.com.agenda.util.StringUtil;
import br.com.agenda.validacoes.ValidacoesContato;
import br.com.agenda.visao.ContatoVisao;

/**
 * 
 * @author bruno.calmon
 *
 */
@ViewScoped
@ManagedBean
public class ContatoMB extends GenericMB {

	private static final long serialVersionUID = -4127295858936642247L;

	private static final Logger LOGGER = Logger.getLogger(ContatoMB.class);
	private static final String CONTATO_VISAO = "contatoVisao";

	@EJB
	private ContatoService contatoService;
	private ContatoVisao contatoVisao;

	/**
	 * Inicializador
	 */
	@PostConstruct
	public void init() {
		this.setContatoVisao((ContatoVisao) super.getFlash(CONTATO_VISAO));

		if (ValidacoesContato.nullOrEmpty(this.getContatoVisao())) {
			this.setContatoVisao(new ContatoVisao());
		} else {
			super.setFlash(CONTATO_VISAO, this.getContatoVisao());
		}
		if (ValidacoesContato.nullOrEmpty(this.getContatoVisao().getContato())) {
			this.getContatoVisao().setContato(new Contato());

			this.getContatoVisao()
					.setListaResultadoContato(contatoService.buscarTodosContatos(this.getContatoVisao().getContato()));
		}
		if (ValidacoesContato.nullOrEmpty(this.getContatoVisao().getTipoBuscaContato())) {
			this.iniciaTipoBuscaContato();
		}
	}

	/**
	 * Persiste dados
	 */
	public String incluirContato() {
		this.getContatoVisao().getContato()
				.setNoContato(StringUtil.limpaEspacosVazios(this.getContatoVisao().getContato().getNoContato()));
		this.getContatoVisao().getContato().setDtEntrada(new Date());

		try {
			ValidacoesContato.validaInclusaoContato(this.getContatoVisao().getContato());
			contatoService.inserir(this.getContatoVisao().getContato());
			super.exibirMsgSucesso(
					"Contato " + this.getContatoVisao().getContato().getNoContato() + " adicionado com sucesso!");

		} catch (AgendaException ae) {
			super.exibirMsgErro(ae.getMessage());
			LOGGER.info(ae);
		}
		this.limpaFiltroContato();
		return redirecionaParaConsultarContato();
	}

	/**
	 * Faz consulta
	 */
	public void consultaContato() {
		if (this.getContatoVisao().getTipoBuscaContatoSelecionado().equals(TipoBuscaContatoEnum.NOME.getId())) {
			this.getContatoVisao()
					.setListaResultadoContato(contatoService.buscarContatoPorNome(getContatoVisao().getContato()));
		}
		if (this.getContatoVisao().getTipoBuscaContatoSelecionado().equals(TipoBuscaContatoEnum.TELEFONE.getId())) {
			this.getContatoVisao()
					.setListaResultadoContato(contatoService.buscarContatoPorTelefone(getContatoVisao().getContato()));
		}
	}

	/**
	 * 
	 * @param contato
	 * @return PAGE_CONSULTA_CONTATO
	 */
	public String editarContato() {
		try {
			ValidacoesContato.validaEdicaoContato(this.getContatoVisao().getContato());
			contatoService.atualizar(this.getContatoVisao().getContato());
			super.exibirMsgSucesso(
					"Contato " + this.getContatoVisao().getContato().getNoContato() + " atualizado com sucesso!");
		} catch (AgendaException ae) {
			super.exibirMsgErro(ae.getMessage());
			LOGGER.info(ae);
		}
		return this.redirecionaParaConsultarContato();
	}

	/**
	 * Exclui contato
	 */
	public void excluirContato(Contato contato) {
		try {
			contatoService.remover(contato);
			init();
		} catch (Exception e) {
			super.exibirMsgErro("Ops! Por algum motivo, não conseguimos remover este contato. =(");
		}
	}
	
	private void iniciaTipoBuscaContato() {
		this.getContatoVisao().setTipoBuscaContato(new ArrayList<SelectItem>());
		for (TipoBuscaContatoEnum e : TipoBuscaContatoEnum.values()) {
			this.getContatoVisao().getTipoBuscaContato().add(new SelectItem(e.getId(), e.getValor()));
		}
	}

	/**
	 * verifica tipo a ser buscado
	 * 
	 * @return inteiro
	 */
	public int verificaTipoDeBusca() {

		if (null != this.getContatoVisao().getTipoBuscaContatoSelecionado()) {
			switch (this.getContatoVisao().getTipoBuscaContatoSelecionado()) {
			case 1:
				return 1;
			case 2:
				return 2;
			default:
				break;
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param contatoVisao
	 * @return String
	 */
	public String redirecionaParaEditarContato(Contato contato) {
		this.getContatoVisao().setContato(contato);
		super.setFlash(CONTATO_VISAO, this.getContatoVisao());
		return PageEnum.PAGE_EDITA_CONTATO.getValor();
	}

	/**
	 * 
	 * @param contatoVisao
	 * @return String
	 */
	public String redirecionaParaIncluirContato() {
		return PageEnum.PAGE_INCLUI_CONTATO.getValor();
	}

	/**
	 * 
	 * @param contatoVisao
	 * @return String
	 */
	public String redirecionaParaConsultarContato() {
		return PageEnum.PAGE_CONSULTA_CONTATO.getValor();
	}

	/**
	 * <p>
	 * Limpa filtro
	 * </p>
	 */
	public void limpaFiltroContato() {
		this.getContatoVisao().setContato(new Contato());
	}

	public ContatoVisao getContatoVisao() {
		return contatoVisao;
	}

	public void setContatoVisao(ContatoVisao contatoVisao) {
		this.contatoVisao = contatoVisao;
	}
}
