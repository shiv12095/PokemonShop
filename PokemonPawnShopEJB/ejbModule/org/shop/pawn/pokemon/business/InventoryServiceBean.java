package org.shop.pawn.pokemon.business;

import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.shop.pawn.pokemon.business.model.Item;
import org.shop.pawn.pokemon.business.view.Inventory;
import org.shop.pawn.pokemon.business.view.InventoryService;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
@Remote(InventoryService.class)
public class InventoryServiceBean implements InventoryService {

	/**
	 * Default constructor.
	 */
	public InventoryServiceBean() {

	}

	@Override
	public Inventory getAvailableInventory() {
		Inventory inventory = new Inventory();
		inventory.add(new Item("mewtwo"));
		inventory.add(new Item("pikachu"));
		inventory.add(new Item("charmander"));
		inventory.add(new Item("meowth"));
		inventory.add(new Item("bulbasaur"));
		inventory.add(new Item("squirtle"));
		return inventory;
	}

	@Override
	public boolean validateQuantity(Collection<Item> items) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updateInventory(Collection<Item> items) {
		// TODO Auto-generated method stub
		return true;
	}

}
