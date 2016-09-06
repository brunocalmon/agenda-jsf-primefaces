package br.com.agenda.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jboss.logging.Logger;

import br.com.agenda.entity.Contato;
import br.com.agenda.entity.Lembrete;
import br.com.agenda.exceptions.AgendaException;
import br.com.agenda.service.LembreteService;
import br.com.agenda.validacoes.ValidacoesContato;
import br.com.agenda.validacoes.ValidacoesLembrete;

@ViewScoped
@ManagedBean(name = "lembreteBean")
public class LembreteBean extends GenericBean {

	private static final long serialVersionUID = 1674348933662083014L;

	private static final Logger LOGGER = Logger.getLogger(LembreteBean.class);

	private Lembrete lembrete;

	@PostConstruct
	private void init() {
		if (ValidacoesContato.nullOrEmpty(getLembrete())) {
			setLembrete(new Lembrete());
		}
	}

	@EJB
	private LembreteService lembreteService;

	public void incluirLembrete() {
		getLembrete().setNoLembrete(limpaEspacosVazios(this.lembrete.getNoLembrete()));
		getLembrete().setDeLembrete(limpaEspacosVazios(this.lembrete.getDeLembrete()));

		getLembrete().setDtLembrete(new Date());

		try {
			ValidacoesLembrete.validaInclusaoLembrete(this.lembrete);
			lembreteService.inserir(this.lembrete);
			exibirMsgSucesso("Lembrete " + getLembrete().getNoLembrete() + " adicionado com sucesso!");

		} catch (AgendaException ae) {
			exibirMsgErro(ae.getMessage());
		} catch (Exception e) {
			LOGGER.info(e);
		} finally {
			setLembrete(new Lembrete());
		}

	}

	public Lembrete getLembrete() {
		return lembrete;
	}

	public void setLembrete(Lembrete lembrete) {
		this.lembrete = lembrete;
	}
}
