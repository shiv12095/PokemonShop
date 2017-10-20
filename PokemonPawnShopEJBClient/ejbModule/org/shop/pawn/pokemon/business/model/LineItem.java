package org.shop.pawn.pokemon.business.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")

@Entity
@Table(name="CUSTOMER_ORDER_LINE_ITEM")
public class LineItem implements Serializable{
	
	public LineItem() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")	
	private int id;

	@Column(name="ITEM_ID")
	private int itemId;
	
	@Column(name="ITEM_NAME")
	private String name;
	
	@Column(name="ITEM_QUANTITY")
	private int quantity;
	
	@Column(name="CUSTOMER_ORDER_ID_FK")
	private int orderId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
