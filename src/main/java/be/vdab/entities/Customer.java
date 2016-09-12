package be.vdab.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import be.vdab.exceptions.ToysException;
import be.vdab.util.Invoercontrole;
import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@Embedded
	private Adres adres;

	// CONSTRUCTORS
	protected Customer() {

	}

	public Customer(String name, Adres adres) throws ToysException {
		setName(name);
		setAdres(adres);
	}

	// GETTERS & SETTERS
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setName(String name) throws ToysException {
		if (Invoercontrole.isStringNotNullOrEmpty(name)) {
			this.name = name;
		} else {
			throw new ToysException("Customer name cannot be empty or null");
		}
	}

	public void setAdres(Adres adres) throws NullPointerException {
		this.adres = Objects.requireNonNull(adres, "adres cannot be null");
	}

	// OVERRIDES
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", adres=" + adres + "]";
	}

}
