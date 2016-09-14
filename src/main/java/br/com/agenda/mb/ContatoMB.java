package br.com.agenda.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Contato;
import br.com.agenda.entity.Telefone;
import br.com.agenda.enums.PageEnum;
import br.com.agenda.enums.TipoBuscaContatoEnum;
import br.com.agenda.exceptions.AgendaException;
import br.com.agenda.service.ContatoService;
import br.com.agenda.service.TelefoneService;
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
	@EJB
	private TelefoneService telefoneService;

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

			this.getContatoVisao().setListaResultadoContato(contatoService.buscarTodosContatosComTelefones());
		}
		if (ValidacoesContato.nullOrEmpty(this.getContatoVisao().getTipoBuscaContato())) {
			this.iniciaTipoBuscaContato();
		}

		if (ValidacoesContato.nullOrEmpty(this.getContatoVisao().getContato().getListaTelefone())) {
			this.getContatoVisao().getContato().setListaTelefone(new ArrayList<Telefone>());
			adicionaNovoTelefoneLista();
		}

		if (null == getContatoVisao().getAcao()) {
			getContatoVisao().setAcao(0);
		}
	}

	/**
	 * add novo tel
	 */
	public void adicionaNovoTelefoneLista() {
		Telefone telefone = new Telefone();
		telefone.setDtTelefone(new Date());
		telefone.setContato(getContatoVisao().getContato());
		getContatoVisao().getContato().getListaTelefone().add(telefone);
	}

	/**
	 * 
	 * @return String
	 */
	public String incluirContato() {
		this.getContatoVisao().getContato()
				.setNoContato(StringUtil.limpaEspacosVazios(this.getContatoVisao().getContato().getNoContato()));

		this.getContatoVisao().getContato().setDtEntrada(new Date());

		try {
			this.setContatoVisao(ValidacoesContato.processarTelefones(this.getContatoVisao(), this.telefoneService));

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
	 * 
	 * @param contato
	 * @return PAGE_CONSULTA_CONTATO
	 */
	public String editarContato() {
		this.getContatoVisao().getContato()
				.setNoContato(StringUtil.limpaEspacosVazios(this.getContatoVisao().getContato().getNoContato()));

		try {
			this.setContatoVisao(ValidacoesContato.processarTelefones(this.getContatoVisao(), this.telefoneService));

			ValidacoesContato.validaInclusaoContato(this.getContatoVisao().getContato());

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
	 * 
	 * @param telefone
	 * @return List
	 */
	public void verificaTelefoneDuplicadosEmTela(List<Telefone> telefone) {

		if (ValidacoesContato.verificaTelefonesDuplicadosEmTela(telefone)) {
			super.exibirMsgErro("Telefone duplicado.");
		}
	}

	/**
	 * Faz consulta
	 */
	public void consultaContato() {
		if (this.isName()) {
			this.getContatoVisao()
					.setListaResultadoContato(contatoService.buscarContatoPorNome(getContatoVisao().getNome()));
		} else if (this.isTel()) {
			this.getContatoVisao().setListaResultadoContato(contatoService.buscarContatoPorTelefone(
					StringUtil.desformatString("(##) ####-####", getContatoVisao().getTelefone())));
		} else {
			super.exibirMsgErro("Selecione uma forma de pesquisa.");
		}
	}

	/**
	 * Exclui contato
	 * 
	 * @param contato
	 */
	public void excluirContato() {
		try {
			contatoService.remover(getContatoVisao().getContatoExcluir());
			init();
		} catch (Exception e) {
			super.exibirMsgErro("Ops! Por algum motivo, não conseguimos remover este contato.");
			LOGGER.info(e);
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
	 * @param telefone
	 */
	public void removeTelefoneParaIncluir(Telefone telefone) {
		if (getContatoVisao().getContato().getListaTelefone().size() > 1) {
			getContatoVisao().getContato().getListaTelefone().remove(telefone);
		} else {
			super.exibirMsgErro("Precisa adicionar pelo menos um telefone");
		}
	}

	/**
	 * 
	 * @param contato
	 * @return PageEnum.PAGE_EDITA_CONTATO.getValor()
	 */
	public String redirecionaParaEditarContato(Contato contato) {
		getContatoVisao().setAcao(2);
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
		getContatoVisao().setAcao(1);
		return PageEnum.PAGE_INCLUI_CONTATO.getValor();
	}

	/**
	 * 
	 * @param contatoVisao
	 * @return String
	 */
	public String redirecionaParaConsultarContato() {
		getContatoVisao().setAcao(0);
		return PageEnum.PAGE_CONSULTA_CONTATO.getValor();
	}

	/**
	 * <p>
	 * Limpa filtro
	 * </p>
	 */
	public void limpaFiltroContato() {
		this.getContatoVisao().setNome(null);
		this.getContatoVisao().setTelefone(null);
	}

	public ContatoVisao getContatoVisao() {
		return contatoVisao;
	}

	public void setContatoVisao(ContatoVisao contatoVisao) {
		this.contatoVisao = contatoVisao;
	}

	private boolean isTel() {
		return TipoBuscaContatoEnum.TELEFONE.getId().equals(this.getContatoVisao().getTipoBuscaContatoSelecionado());
	}

	private boolean isName() {
		return TipoBuscaContatoEnum.NOME.getId().equals(this.getContatoVisao().getTipoBuscaContatoSelecionado());
	}
}
