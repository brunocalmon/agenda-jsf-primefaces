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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author bruno.calmon
 *
 */
@Entity
@Table(name = "contato", schema = "agd")
@SequenceGenerator(name = "contato_sequence", sequenceName = "contato_sequence", allocationSize = 1, initialValue = 0)
public class Contato implements Serializable, Entidade{

	private static final long serialVersionUID = 3269376878922748348L;

	@Id
	@GeneratedValue
	@Column(name = "id_contato")
	private Long idContato;

	@Column(name = "no_contato")
	private String noContato;

	@Column(name = "dt_entrada")
	private Date dtEntrada;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "contato", targetEntity = Telefone.class, orphanRemoval = true)
	private List<Telefone> listaTelefone;

	public Long getIdContato() {
		return idContato;
	}

	public void setIdContato(Long idContato) {
		this.idContato = idContato;
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

	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	@Override
	public Object getPk() {
		return idContato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtEntrada == null) ? 0 : dtEntrada.hashCode());
		result = prime * result + ((idContato == null) ? 0 : idContato.hashCode());
		result = prime * result + ((listaTelefone == null) ? 0 : listaTelefone.hashCode());
		result = prime * result + ((noContato == null) ? 0 : noContato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
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
		if (idContato == null) {
			if (other.idContato != null)
				return false;
		} else if (!idContato.equals(other.idContato))
			return false;
		if (listaTelefone == null) {
			if (other.listaTelefone != null)
				return false;
		} else if (!listaTelefone.equals(other.listaTelefone))
			return false;
		if (noContato == null) {
			if (other.noContato != null)
				return false;
		} else if (!noContato.equals(other.noContato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contato [idContato=" + idContato + ", noContato=" + noContato + ", dtEntrada=" + dtEntrada
				+ ", listaTelefone=" + listaTelefone + "]";
	}

}
