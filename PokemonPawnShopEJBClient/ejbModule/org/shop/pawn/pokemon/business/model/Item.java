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
@Table(name="ITEM")
public class Item implements Serializable{
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="NAME")
	private String name;

	@Column(name="AVAILABLE_QUANTITY")
	private String quantity;

	public Item() {

	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
		
	
	public Item(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public boolean isValid() {
		if (name == null && name.isEmpty()) {
			return false;
		}
		if (quantity == null || quantity.isEmpty()) {
			return false;
		}
		try {
			int num = Integer.parseInt(quantity);
			if (num < 0) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
}
