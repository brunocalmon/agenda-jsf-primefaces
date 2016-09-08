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

	@EJB
	private ContatoService contatoService;
	private Contato contato;
	private ContatoVisao contatoVisao;

	/**
	 * Inicializador
	 */
	@PostConstruct
	public void init() {
		setContatoVisao((ContatoVisao) getFlash("contatoVisao"));

		// if (ValidacoesContato.nullOrEmpty(getContato())) {
		// setContato(new Contato());
		// }
		if (ValidacoesContato.nullOrEmpty(getContatoVisao())) {
			setContatoVisao(new ContatoVisao());
		}
		if (ValidacoesContato.nullOrEmpty(getContatoVisao().getContato())) {
			getContatoVisao().setContato(new Contato());
		}
		if (ValidacoesContato.nullOrEmpty(getContatoVisao().getTipoBuscaContato())) {
			iniciaTipoBuscaContato();
		}
	}

	/**
	 * Persiste dados
	 */
	public void incluirContato() {
		getContatoVisao().getContato()
				.setNoContato(StringUtil.limpaEspacosVazios(getContatoVisao().getContato().getNoContato()));
		getContatoVisao().getContato().setDtEntrada(new Date());

		try {
			ValidacoesContato.validaInclusaoContato(getContatoVisao().getContato());
			contatoService.inserir(getContatoVisao().getContato());
			exibirMsgSucesso("Contato " + getContatoVisao().getContato().getNoContato() + " adicionado com sucesso!");

		} catch (AgendaException ae) {
			exibirMsgErro(ae.getMessage());
			LOGGER.info(ae);
		} catch (Exception e) {
			LOGGER.info(e);
		} finally {
			getContatoVisao().setContato(new Contato());
		}
	}

	/**
	 * Faz consulta
	 */
	public void consultaContato() {
		if (getContatoVisao().getTipoBuscaContatoSelecionado().equals(TipoBuscaContatoEnum.NOME.getId())) {
			getContatoVisao()
					.setListaResultadoContato(contatoService.buscarContatoPorNome(getContatoVisao().getContato()));
		}
		if (getContatoVisao().getTipoBuscaContatoSelecionado().equals(TipoBuscaContatoEnum.TELEFONE.getId())) {
			getContatoVisao()
					.setListaResultadoContato(contatoService.buscarContatoPorTelefone(getContatoVisao().getContato()));
		}
	}

	/**
	 * 
	 * @param contato
	 */
	public void editarContato(Contato contato) {
		contatoService.atualizar(contato);
	}

	private void iniciaTipoBuscaContato() {
		getContatoVisao().setTipoBuscaContato(new ArrayList<SelectItem>());
		for (TipoBuscaContatoEnum e : TipoBuscaContatoEnum.values()) {
			getContatoVisao().getTipoBuscaContato().add(new SelectItem(e.getId(), e.getValor()));
		}
	}

	/**
	 * verifica tipo a ser buscado
	 * 
	 * @return inteiro
	 */
	public int verificaTipoDeBusca() {

		if (null != getContatoVisao().getTipoBuscaContatoSelecionado()) {
			switch (getContatoVisao().getTipoBuscaContatoSelecionado()) {
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
		getContatoVisao().setContato(contato);
		setFlash("contatoVisao", getContatoVisao());
		return "/pages/contato/editarContato.xhtml";
	}

	/**
	 * <p>
	 * Limpa filtro
	 * </p>
	 */
	public void limpaFiltroConsulta() {
		contatoVisao.setContato(new Contato());
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public ContatoVisao getContatoVisao() {
		return contatoVisao;
	}

	public void setContatoVisao(ContatoVisao contatoVisao) {
		this.contatoVisao = contatoVisao;
	}
}
