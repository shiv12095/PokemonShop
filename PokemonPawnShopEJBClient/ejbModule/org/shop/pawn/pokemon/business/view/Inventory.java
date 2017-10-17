package org.shop.pawn.pokemon.business.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.shop.pawn.pokemon.business.model.Item;

@SuppressWarnings("serial")
public class Inventory implements Serializable {

	private List<Item> items;

	public Inventory() {
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
	
}
