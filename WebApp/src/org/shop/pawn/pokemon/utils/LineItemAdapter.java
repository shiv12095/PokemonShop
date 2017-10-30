package org.shop.pawn.pokemon.utils;

import org.shop.pawn.pokemon.business.model.Item;
import org.shop.pawn.pokemon.business.model.LineItem;

public class LineItemAdapter {
	
	public static LineItem getLineItem(Item item) {
		LineItem lineItem = new LineItem();
		lineItem.setName(item.getName());
		lineItem.setQuantity(Integer.parseInt(item.getQuantity()));
		return lineItem;
	}
	
	public static Item getItem(LineItem lineItem) {
		Item item = new Item();
		item.setName(lineItem.getName());
		item.setQuantity(Integer.toString(lineItem.getQuantity()));
		item.setId(lineItem.getItemId());
		return item;
	}

}
