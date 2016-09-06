package br.com.agenda.bean;

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
import br.com.agenda.validacoes.ValidacoesContato;
import br.com.agenda.visao.ContatoVisao;

@ViewScoped
@ManagedBean(name = "contatoBean")
public class ContatoBean extends GenericBean {

	private static final long serialVersionUID = -4127295858936642247L;

	private static final Logger LOGGER = Logger.getLogger(ContatoBean.class);

	@EJB
	private ContatoService contatoService;
	private Contato contato;
	private ContatoVisao contatoVisao;

	@PostConstruct
	public void init() {
		if (ValidacoesContato.nullOrEmpty(getContato())) {
			setContato(new Contato());
		}
		if (ValidacoesContato.nullOrEmpty(getContatoVisao())) {
			setContatoVisao(new ContatoVisao());
		}
		if (ValidacoesContato.nullOrEmpty(getContatoVisao().getTipoBuscaContato())){
			iniciaTipoBuscaContato();
		}
	}
	
	public void incluirContato() {
		getContato().setNoContato(limpaEspacosVazios(getContato().getNoContato()));
		getContato().setDtEntrada(new Date());

		try {
			ValidacoesContato.validaInclusaoContato(getContato());
			contatoService.inserir(getContato());
			exibirMsgSucesso("Contato " + getContato().getNoContato() + " adicionado com sucesso!");

		} catch (AgendaException ae) {
			exibirMsgErro(ae.getMessage());
		} catch (Exception e) {
			LOGGER.info(e);
		} finally {
			setContato(new Contato());
		}
	}
	
	public void consultaContato(){
		
	}

	private void iniciaTipoBuscaContato() {
		getContatoVisao().setTipoBuscaContato(new ArrayList<SelectItem>());
		for (TipoBuscaContatoEnum e : TipoBuscaContatoEnum.values()) {
			getContatoVisao().getTipoBuscaContato().add(new SelectItem(e.getId(), e.getValor()));
		}
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
