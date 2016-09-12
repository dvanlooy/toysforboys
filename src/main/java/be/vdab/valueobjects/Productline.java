package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import be.vdab.util.Invoercontrole;

@Entity
@Table(name = "productlines")
public class Productline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String description;

	// CONSTRUCTORS
	protected Productline() {

	}

	public Productline(String name, String description) throws IllegalArgumentException {
		setName(name);
		setDescription(description);
	}

	// GETTERS & SETTERS
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) throws IllegalArgumentException {
		this.name = Invoercontrole.noEmptyOrNullString(name, "productline name cannot be empty or null");
	}

	public void setDescription(String description) {
		this.description = description; // geen invoercontrole: vrij veld
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
		if (!(obj instanceof Productline))
			return false;
		Productline other = (Productline) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
