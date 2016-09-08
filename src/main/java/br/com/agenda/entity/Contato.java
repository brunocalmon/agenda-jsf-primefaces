package br.com.agenda.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author bruno.calmon
 *
 */
@Entity
@Table(name = "contato", schema = "public")
public class Contato implements Serializable {

	private static final long serialVersionUID = 3269376878922748348L;

	@Id
	@GeneratedValue
	@Column(name = "nu_contato")
	private Long nuContato;

	@Column(name = "no_contato")
	private String noContato;

	@Column(name = "nu_telefone")
	private String nuTelefone;

	@Column(name = "dt_entrada")
	private Date dtEntrada;

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

	public String getNuTelefone() {
		return nuTelefone;
	}

	public void setNuTelefone(String nuTelefone) {
		this.nuTelefone = nuTelefone;
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
		result = prime * result + ((dtEntrada == null) ? 0 : dtEntrada.hashCode());
		result = prime * result + ((noContato == null) ? 0 : noContato.hashCode());
		result = prime * result + ((nuContato == null) ? 0 : nuContato.hashCode());
		result = prime * result + ((nuTelefone == null) ? 0 : nuTelefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Contato))
			return false;
		Contato other = (Contato) obj;
		if (dtEntrada == null) {
			if (other.dtEntrada != null)
				return false;
		} else if (!dtEntrada.equals(other.dtEntrada))
			return false;
		if (noContato == null) {
			if (other.noContato != null)
				return false;
		} else if (!noContato.equals(other.noContato))
			return false;
		if (nuContato == null) {
			if (other.nuContato != null)
				return false;
		} else if (!nuContato.equals(other.nuContato))
			return false;
		if (nuTelefone == null) {
			if (other.nuTelefone != null)
				return false;
		} else if (!nuTelefone.equals(other.nuTelefone))
			return false;
		return true;
	}
}
