package br.com.agenda.entity;

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
@Table(name = "lembrete", schema = "agd")
public class Lembrete {

	@Id
	@GeneratedValue
	@Column(name = "nu_lembrete")
	private Long nuLembrete;

	@Column(name = "no_lembrete")
	private String noLembrete;

	@Column(name = "de_lembrete", length=1000)
	private String deLembrete;

	@Column(name = "dt_lembrete")
	private Date dtLembrete;

	public Long getNuLembrete() {
		return nuLembrete;
	}

	public void setNuLembrete(Long nuLembrete) {
		this.nuLembrete = nuLembrete;
	}

	public String getNoLembrete() {
		return noLembrete;
	}

	public void setNoLembrete(String noLembrete) {
		this.noLembrete = noLembrete;
	}

	public String getDeLembrete() {
		return deLembrete;
	}

	public void setDeLembrete(String deLembrete) {
		this.deLembrete = deLembrete;
	}

	public Date getDtLembrete() {
		return dtLembrete;
	}

	public void setDtLembrete(Date dtLembrete) {
		this.dtLembrete = dtLembrete;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deLembrete == null) ? 0 : deLembrete.hashCode());
		result = prime * result + ((dtLembrete == null) ? 0 : dtLembrete.hashCode());
		result = prime * result + ((noLembrete == null) ? 0 : noLembrete.hashCode());
		result = prime * result + ((nuLembrete == null) ? 0 : nuLembrete.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Lembrete))
			return false;
		Lembrete other = (Lembrete) obj;
		if (deLembrete == null) {
			if (other.deLembrete != null)
				return false;
		} else if (!deLembrete.equals(other.deLembrete))
			return false;
		if (dtLembrete == null) {
			if (other.dtLembrete != null)
				return false;
		} else if (!dtLembrete.equals(other.dtLembrete))
			return false;
		if (noLembrete == null) {
			if (other.noLembrete != null)
				return false;
		} else if (!noLembrete.equals(other.noLembrete))
			return false;
		if (nuLembrete == null) {
			if (other.nuLembrete != null)
				return false;
		} else if (!nuLembrete.equals(other.nuLembrete))
			return false;
		return true;
	}
}
