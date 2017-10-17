package org.shop.pawn.pokemon.business.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable{

	private String name;
	private String quantity;

	public Item() {

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
