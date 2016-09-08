package br.com.agenda.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tarefa implements Serializable {
	
	private static final long serialVersionUID = 7398511308245141525L;

	@Id
	@GeneratedValue
	@Column(name = "nu_tarefa")
	private Long nuTarefa;
	
	@Column(name = "no_tarefa")
	private String noTarefa;
	
	@Column(name = "de_tarefa")
	private String deTarefa;
	
	@Column(name = "dt_tarefa")
	private Date dtTarefa;

	public Long getNuTarefa() {
		return nuTarefa;
	}

	public void setNuTarefa(Long nuTarefa) {
		this.nuTarefa = nuTarefa;
	}

	public String getNoTarefa() {
		return noTarefa;
	}

	public void setNoTarefa(String noTarefa) {
		this.noTarefa = noTarefa;
	}

	public String getDeTarefa() {
		return deTarefa;
	}

	public void setDeTarefa(String deTarefa) {
		this.deTarefa = deTarefa;
	}

	public Date getDtTarefa() {
		return dtTarefa;
	}

	public void setDtTarefa(Date dtTarefa) {
		this.dtTarefa = dtTarefa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deTarefa == null) ? 0 : deTarefa.hashCode());
		result = prime * result + ((dtTarefa == null) ? 0 : dtTarefa.hashCode());
		result = prime * result + ((noTarefa == null) ? 0 : noTarefa.hashCode());
		result = prime * result + ((nuTarefa == null) ? 0 : nuTarefa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Tarefa))
			return false;
		Tarefa other = (Tarefa) obj;
		if (deTarefa == null) {
			if (other.deTarefa != null)
				return false;
		} else if (!deTarefa.equals(other.deTarefa))
			return false;
		if (dtTarefa == null) {
			if (other.dtTarefa != null)
				return false;
		} else if (!dtTarefa.equals(other.dtTarefa))
			return false;
		if (noTarefa == null) {
			if (other.noTarefa != null)
				return false;
		} else if (!noTarefa.equals(other.noTarefa))
			return false;
		if (nuTarefa == null) {
			if (other.nuTarefa != null)
				return false;
		} else if (!nuTarefa.equals(other.nuTarefa))
			return false;
		return true;
	}
}
