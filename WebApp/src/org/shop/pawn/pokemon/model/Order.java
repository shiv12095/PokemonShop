package org.shop.pawn.pokemon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.shop.pawn.pokemon.business.model.LineItem;

@Entity
@Table(name="CUSTOMER_ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Column(name="CUSTOMER_EMAIL")	
	private String customerEmail;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PAYMENT_INFO_ID_FK")
	private PaymentInfo payment;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_ORDER_ID_FK")
	private List<LineItem> items;
	
	public Order() {
		this.items = new ArrayList<LineItem>();
	}

	public List<LineItem> getItems() {
		return items;
	}

	public void setItems(List<LineItem> items) {
		this.items = items;
	}

	public void add(LineItem item) {
		this.items.add(item);
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}		

	public PaymentInfo getPayment() {
		return payment;
	}

	public void setPayment(PaymentInfo payment) {
		this.payment = payment;
	}

	public boolean isValid() {
		if (this.items == null || this.items.isEmpty()) {
			return false;
		}
		return true;
	}
}
