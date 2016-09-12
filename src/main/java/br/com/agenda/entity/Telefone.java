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
@Table(name = "telefone", schema = "agd")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 3269376878922748348L;

	@Id
	@GeneratedValue
	@Column(name = "id_telefone")
	private Long idTelefone;

	@Column(name = "nu_telefone")
	private String nuTelefone;

	@Column(name = "dt_telefone")
	private Date dtTelefone;

	public Long getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(Long idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getNuTelefone() {
		return nuTelefone;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTelefone == null) ? 0 : idTelefone.hashCode());
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
		Telefone other = (Telefone) obj;
		if (idTelefone == null) {
			if (other.idTelefone != null)
				return false;
		} else if (!idTelefone.equals(other.idTelefone))
			return false;
		return true;
	}

}
