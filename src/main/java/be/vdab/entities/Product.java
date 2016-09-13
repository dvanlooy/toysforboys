package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import be.vdab.exceptions.UnshippedException;
import be.vdab.util.Invoercontrole;
import be.vdab.valueobjects.Productline;

@Entity
@Table(name = "products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String scale;
	private String description;
	private long quantityInStock;
	private long quantityInOrder;
	private BigDecimal buyPrice;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productlineId")
	private Productline productline;
	
	@Transient
	private final static Logger logger = Logger.getLogger(Product.class.getName());

	// FUNCTIONAL METHODS

	/**
	 * Reduces quantityInStock and quantityInOrder with long quantityOrdered
	 * parameter
	 * 
	 * @param quantityOrdered
	 * @throws UnshippedException,
	 *             ToysException
	 */
	public void ship(long quantityOrdered) throws UnshippedException, IllegalArgumentException {

		if (quantityOrdered <= quantityInStock) {
			setQuantityInStock(quantityInStock - quantityOrdered);
			if (quantityOrdered <= quantityInOrder) {
				setQuantityInOrder(quantityInOrder - quantityOrdered);
			} else {
				// Corrupted data in database, do something...
				logger.log(Level.WARNING, "Corrupted quantityInOrder for " + this.getName());
			}
		} else {
			throw new UnshippedException("Shipping failed for " + this.toString());
		}
	}

	// CONSTRUCTORS
	protected Product() {

	}

	public Product(String name, String scale, String description, long quantityInStock, long quantityInOrder,
			BigDecimal buyPrice, Productline productline) throws IllegalArgumentException, NullPointerException {

		setName(name);
		setScale(scale);
		setDescription(description);
		setQuantityInStock(quantityInStock);
		setQuantityInOrder(quantityInOrder);
		setBuyPrice(buyPrice);
		setProductline(productline);
	}

	// GETTERS
	public long getProductID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getScale() {
		return scale;
	}

	public String getDescription() {
		return description;
	}

	public long getQuantityInStock() {
		return quantityInStock;
	}

	public long getQuantityInOrder() {
		return quantityInOrder;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public Productline getProductline() {
		return productline;
	}

	public void setName(String name) throws IllegalArgumentException {
		this.name = Invoercontrole.noEmptyOrNullString(name, "product name cannot be empty or null");
	}

	public void setScale(String scale) throws IllegalArgumentException {
		this.scale = Invoercontrole.noEmptyOrNullString(scale, "scale cannot be empty or null");
	}

	public void setDescription(String description) {
		this.description = description; // geen invoercontrole: vrij veld
	}

	public void setQuantityInStock(long quantityInStock) throws IllegalArgumentException {
		this.quantityInStock = Invoercontrole.positiveLong(quantityInStock, "quantity In Stock cannot be negative");
	}

	public void setQuantityInOrder(long quantityInOrder) throws IllegalArgumentException {
		this.quantityInOrder = Invoercontrole.positiveLong(quantityInOrder, "quantity In Order cannot be negative");
	}

	public void setBuyPrice(BigDecimal buyPrice) throws IllegalArgumentException {
		this.buyPrice = Invoercontrole.positiveBigDecimal(buyPrice, "buyPrice cannot be negative");
	}

	public void setProductline(Productline productline) throws NullPointerException {
		this.productline = Objects.requireNonNull(productline, "productline cannot be null");
	}

	// OVERRIDES

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyPrice == null) ? 0 : buyPrice.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((productline == null) ? 0 : productline.hashCode());
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (buyPrice == null) {
			if (other.buyPrice != null)
				return false;
		} else if (!buyPrice.equals(other.buyPrice))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (productline == null) {
			if (other.productline != null)
				return false;
		} else if (!productline.equals(other.productline))
			return false;
		if (scale == null) {
			if (other.scale != null)
				return false;
		} else if (!scale.equals(other.scale))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", scale=" + scale + ", description=" + description
				+ ", quantityInStock=" + quantityInStock + ", quantityInOrder=" + quantityInOrder + ", buyPrice="
				+ buyPrice + ", productline=" + productline + "]";
	}

}
