package be.vdab.entities;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.engine.profile.Fetch;

import be.vdab.exceptions.UnshippedException;
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

//	// LOGGER MEMBERS
//	@Transient
//	private final Logger logger = Logger.getLogger(Product.class.getName());
//	@Transient
//	private static FileHandler fh = null;
//	@Transient
//	private final static String LOGFILE_LOCATION = "C:/logs/toysforboyslog.log";

	// FUNCTIONAL METHODS

//	/**
//	 * creates a logfile
//	 */
//	private void createLogFile() {
//		try {
//			fh = new FileHandler(LOGFILE_LOCATION);
//			logger.addHandler(fh);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Reduces quantityInStock and quantityInOrder with long quantityOrdered
	 * parameter
	 * 
	 * @param quantityOrdered
	 * @throws UnshippedException
	 */
	public void ship(long quantityOrdered) throws UnshippedException {
		if (quantityOrdered <= quantityInStock) {
			if (quantityInOrder < quantityOrdered) {
//				logger.warning("Anomaly in quantityInOrder column for " + getName());
				setQuantityInStock(quantityInStock - quantityOrdered);				
			}else{
			setQuantityInOrder(quantityInOrder - quantityOrdered);
			setQuantityInStock(quantityInStock - quantityOrdered);
			}
			// om te testen of logger werkt
//			logger.info("Quantity in stock adjusted for " + getName());
		} else {
			throw new UnshippedException();
		}
	}

	// CONSTRUCTORS
	protected Product() {
//		createLogFile();
	}

	public Product(String name, String scale, String description, long quantityInStock, long quantityInOrder,
			BigDecimal buyPrice, Productline productline) {
//		createLogFile();
		this.name = name;
		this.scale = scale;
		this.description = description;
		this.quantityInStock = quantityInStock;
		this.quantityInOrder = quantityInOrder;
		this.buyPrice = buyPrice;
		this.productline = productline;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setQuantityInStock(long quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public void setQuantityInOrder(long quantityInOrder) {
		this.quantityInOrder = quantityInOrder;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public void setProductline(Productline productline) {
		this.productline = productline;
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

}
