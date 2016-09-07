package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
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

	// CONSTRUCTORS

	protected Order() {

	}

	public Order(long id, Date orderDate, Date requiredDate, Date shippedDate, String comments, Customer customer,
			Status status) {
		this.id = id;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.comments = comments;
		this.customer = customer;
		this.status = status;
	}

	// GETTERS

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

	public Set<Orderdetail> getOrderdetails() {
		return Collections.unmodifiableSet(orderdetails);
	}

}
