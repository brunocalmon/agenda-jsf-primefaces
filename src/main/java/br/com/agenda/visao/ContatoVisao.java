package br.com.agenda.visao;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.agenda.entity.Contato;
import br.com.agenda.entity.Telefone;

/**
 * 
 * @author bruno.calmon
 *
 */
public class ContatoVisao {

	private Contato contato;
	private String nome;
	private String telefone;
	private String email;
	private List<SelectItem> tipoBuscaContato;
	private List<SelectItem> selectItemTelefones;
	private List<Contato> listaResultadoContato;
	private Integer tipoBuscaContatoSelecionado;
	private Telefone telefoneSelecionado;

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<SelectItem> getTipoBuscaContato() {
		return tipoBuscaContato;
	}

	public void setTipoBuscaContato(List<SelectItem> tipoBuscaContato) {
		this.tipoBuscaContato = tipoBuscaContato;
	}

	public Integer getTipoBuscaContatoSelecionado() {
		return tipoBuscaContatoSelecionado;
	}

	public void setTipoBuscaContatoSelecionado(Integer tipoBuscaContatoSelecionado) {
		this.tipoBuscaContatoSelecionado = tipoBuscaContatoSelecionado;
	}

	public List<Contato> getListaResultadoContato() {
		return listaResultadoContato;
	}

	public void setListaResultadoContato(List<Contato> listaResultadoContato) {
		this.listaResultadoContato = listaResultadoContato;
	}

	public Telefone getTelefoneSelecionado() {
		return telefoneSelecionado;
	}

	public void setTelefoneSelecionado(Telefone telefoneSelecionado) {
		this.telefoneSelecionado = telefoneSelecionado;
	}

	public List<SelectItem> getSelectItemTelefones() {
		return selectItemTelefones;
	}

	public void setSelectItemTelefones(List<SelectItem> selectItemTelefones) {
		this.selectItemTelefones = selectItemTelefones;
	}
}
