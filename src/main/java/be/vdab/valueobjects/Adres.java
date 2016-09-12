package be.vdab.valueobjects;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.util.Invoercontrole;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	private String streetAndNumber;
	private String city;
	private String state;
	private String postalCode;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "countryId")
	private Country country;

	// CONSTRUCTORS
	protected Adres() {

	}

	public Adres(String streetAndNumber, String city, String state, String postalCode, Country country)
			throws IllegalArgumentException, NullPointerException {
		setStreetAndNumber(streetAndNumber);
		setCity(city);
		setState(state);
		setPostalCode(postalCode);
		setCountry(country);
	}

	// GETTERS & SETTERS
	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setStreetAndNumber(String streetAndNumber) throws IllegalArgumentException {
		this.streetAndNumber = Invoercontrole.noEmptyOrNullString(streetAndNumber,
				"streetAndNumber cannot be empty or null");
	}

	public void setCity(String city) throws IllegalArgumentException {
		this.city = Invoercontrole.noEmptyOrNullString(city, "city cannot be empty or null");
	}

	public void setState(String state) throws IllegalArgumentException {
		this.state = Invoercontrole.noEmptyOrNullString(state, "state cannot be empty or null");
	}

	public void setPostalCode(String postalCode) throws IllegalArgumentException {
		this.postalCode = Invoercontrole.noEmptyOrNullString(postalCode, "postalCode cannot be empty or null");
	}

	public void setCountry(Country country) throws NullPointerException {
		this.country = Objects.requireNonNull(country, "country cannot be null");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((streetAndNumber == null) ? 0 : streetAndNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Adres))
			return false;
		Adres other = (Adres) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAndNumber == null) {
			if (other.streetAndNumber != null)
				return false;
		} else if (!streetAndNumber.equals(other.streetAndNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adres [streetAndNumber=" + streetAndNumber + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + ", country=" + country + "]";
	}

}
