package org.shop.pawn.pokemon.business.view;

import java.util.Collection;

import org.shop.pawn.pokemon.business.model.Item;

public interface InventoryService {

	 public Inventory getAvailableInventory();
	 
	 public boolean validateQuantity(Collection<Item> items);
	 
	 public boolean updateInventory(Collection<Item> items);	
}
