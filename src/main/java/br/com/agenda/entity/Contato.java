package br.com.agenda.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author bruno.calmon
 *
 */
@Entity
@Table(name = "contato", schema = "agd")
public class Contato implements Serializable {

	private static final long serialVersionUID = 3269376878922748348L;

	@Id
	@GeneratedValue
	@Column(name = "nu_contato")
	private Long nuContato;

	@Column(name = "no_contato")
	private String noContato;

	@Column(name = "dt_entrada")
	private Date dtEntrada;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "pk.telefone", targetEntity = ContatoTelefone.class)
	private List<ContatoTelefone> listaTelefone;

	public Long getNuContato() {
		return nuContato;
	}

	public void setNuContato(Long nuContato) {
		this.nuContato = nuContato;
	}

	public String getNoContato() {
		return noContato;
	}

	public void setNoContato(String noContato) {
		this.noContato = noContato;
	}

	public Date getDtEntrada() {
		return dtEntrada;
	}

	public void setDtEntrada(Date dtEntrada) {
		this.dtEntrada = dtEntrada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nuContato == null) ? 0 : nuContato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (nuContato == null) {
			if (other.nuContato != null)
				return false;
		} else if (!nuContato.equals(other.nuContato))
			return false;
		return true;
	}

	public List<ContatoTelefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<ContatoTelefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

}
