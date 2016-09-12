//staat bij valueobjects omdat deze hoort bij Adres, maar een id heeft in de database (=entity)
package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import be.vdab.util.Invoercontrole;

@Entity
@Table(name = "countries")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	// CONSTRUCTORS
	protected Country() {
	}

	public Country(String name) throws IllegalArgumentException {
		setName(name);
	}

	// GETTERS & SETTERS

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws IllegalArgumentException {
		this.name = Invoercontrole.noEmptyOrNullString(name, "country name cannot be empty or null");
	}

	// OVERRIDES
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Country))
			return false;
		Country other = (Country) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}

}
