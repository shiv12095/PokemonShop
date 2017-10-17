package org.shop.pawn.pokemon.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private List<Item> items;

	public Order() {
		this.items = new ArrayList<Item>();
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void add(Item item) {
		this.items.add(item);
	}

	public boolean isValid() {
		if (this.items == null || this.items.isEmpty()) {
			return false;
		}
		for (Item item : items) {
			if (!item.isValid()) {
				return false;
			}
		}
		return true;
	}
}
