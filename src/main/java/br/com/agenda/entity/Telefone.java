package br.com.agenda.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.agenda.util.StringUtil;

/**
 * 
 * @author bruno.calmon
 *
 */
@Entity
@Table(name = "telefone", schema = "agd")
@SequenceGenerator(name = "telefone_sequence", sequenceName = "telefone_sequence", allocationSize = 1, initialValue = 0)
public class Telefone implements Serializable, Entidade {

	private static final long serialVersionUID = 3269376878922748348L;

	@Id
	@GeneratedValue
	@Column(name = "id_telefone")
	private Long idTelefone;

	@Column(name = "nu_telefone")
	private String nuTelefone;

	@Column(name = "dt_telefone")
	private Date dtTelefone;

	@JoinColumn(name = "fk_contato", referencedColumnName = "id_contato")
	@ManyToOne(optional = false, cascade = { CascadeType.REFRESH })
	private Contato contato;

	public Long getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(Long idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getNuTelefone() {
		return nuTelefone;
	}

	public String getNuTelefoneFormatado() {
		return StringUtil.formatString("(##) ####-####", nuTelefone);
	}

	public void setNuTelefone(String nuTelefone) {
		this.nuTelefone = nuTelefone;
	}

	public Date getDtTelefone() {
		return dtTelefone;
	}

	public void setDtTelefone(Date dtTelefone) {
		this.dtTelefone = dtTelefone;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	@Override
	public Object getPk() {
		return idTelefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result + ((dtTelefone == null) ? 0 : dtTelefone.hashCode());
		result = prime * result + ((idTelefone == null) ? 0 : idTelefone.hashCode());
		result = prime * result + ((nuTelefone == null) ? 0 : nuTelefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Telefone))
			return false;
		Telefone other = (Telefone) obj;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (dtTelefone == null) {
			if (other.dtTelefone != null)
				return false;
		} else if (!dtTelefone.equals(other.dtTelefone))
			return false;
		if (idTelefone == null) {
			if (other.idTelefone != null)
				return false;
		} else if (!idTelefone.equals(other.idTelefone))
			return false;
		if (nuTelefone == null) {
			if (other.nuTelefone != null)
				return false;
		} else if (!nuTelefone.equals(other.nuTelefone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Telefone [idTelefone=" + idTelefone + ", nuTelefone=" + nuTelefone + ", dtTelefone=" + dtTelefone
				+ ", contato=" + contato + "]";
	}

}
