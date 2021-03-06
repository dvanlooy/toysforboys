package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.enums.Status;
import be.vdab.exceptions.UnshippedException;
import be.vdab.valueobjects.Orderdetail;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date orderDate;

	@Temporal(TemporalType.DATE)
	private Date requiredDate;

	@Temporal(TemporalType.DATE)
	private Date shippedDate;

	private String comments;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customerId")
	private Customer customer;

	@Enumerated(EnumType.STRING)
	private Status status;

	@ElementCollection
	@CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderId"))
	private Set<Orderdetail> orderdetails;

	// FUNCTIONAL METHODS

	/**
	 * 
	 * @return unmodifiable Set with orderdetail objects
	 */
	public Set<Orderdetail> getOrderdetails() {
		return Collections.unmodifiableSet(orderdetails);
	}

	/**
	 * makes sum of all orderdetail total values
	 * 
	 * @return BigDecimal with total value of this order
	 */

	public BigDecimal getTotalValue() {
		BigDecimal totalValue = BigDecimal.ZERO;
		for (Orderdetail orderdetail : this.getOrderdetails()) {
			totalValue = totalValue.add(orderdetail.getTotalValue());
		}
		return totalValue;
	}

	/**
	 * Sets status on shipped and ships products
	 * 
	 * @throws UnshippedException,
	 *             ToysException
	 */
	public void ship() throws UnshippedException, IllegalArgumentException {
		for (Orderdetail orderdetail : this.getOrderdetails()) {
			orderdetail.getProduct().ship(orderdetail.getQuantityOrdered());
		}
		setStatus(Status.SHIPPED);
		shippedDate = new Date();
	}

	// CONSTRUCTORS
	protected Order() {

	}

	public Order(Date orderDate, Date requiredDate, Date shippedDate, String comments, Customer customer, Status status)
			throws NullPointerException {
		setOrderDate(orderDate);
		setRequiredDate(requiredDate);
		setShippedDate(shippedDate);
		setComments(comments);
		setCustomer(customer);
		setStatus(status);
	}

	// GETTERS & SETTERS
	public long getId() {
		return id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public String getComments() {
		return comments;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Status getStatus() {
		return status;
	}

	public void setOrderDate(Date orderDate) throws NullPointerException {
		this.orderDate = Objects.requireNonNull(orderDate, "orderDate cannot be null");
	}

	public void setRequiredDate(Date requiredDate) throws NullPointerException {
		this.requiredDate = Objects.requireNonNull(requiredDate, "requiredDate cannot be null");
	}

	public void setShippedDate(Date shippedDate) throws NullPointerException {
		this.shippedDate = Objects.requireNonNull(shippedDate, "shippedDate cannot be null");
	}

	public void setComments(String comments) {
		this.comments = comments; // geen invoercontrole: vrij veld
	}

	public void setCustomer(Customer customer) throws NullPointerException {
		this.customer = Objects.requireNonNull(customer, "customer cannot be null");
	}

	public void setStatus(Status status) throws NullPointerException {
		this.status = Objects.requireNonNull(status, "Status cannot be null");
	}

	public void setOrderdetails(Set<Orderdetail> orderdetails) throws NullPointerException {
		this.orderdetails = Objects.requireNonNull(orderdetails, "orderdetails cannot be null");
	}

	// OVERRIDES
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderdetails == null) ? 0 : orderdetails.hashCode());
		result = prime * result + ((requiredDate == null) ? 0 : requiredDate.hashCode());
		result = prime * result + ((shippedDate == null) ? 0 : shippedDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderdetails == null) {
			if (other.orderdetails != null)
				return false;
		} else if (!orderdetails.equals(other.orderdetails))
			return false;
		if (requiredDate == null) {
			if (other.requiredDate != null)
				return false;
		} else if (!requiredDate.equals(other.requiredDate))
			return false;
		if (shippedDate == null) {
			if (other.shippedDate != null)
				return false;
		} else if (!shippedDate.equals(other.shippedDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate + ", shippedDate="
				+ shippedDate + ", comments=" + comments + ", customer=" + customer + ", status=" + status
				+ ", orderdetails=" + orderdetails + "]";
	}

}
