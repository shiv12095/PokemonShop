package org.shop.pawn.pokemon.business;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.shop.pawn.pokemon.business.model.Item;
import org.shop.pawn.pokemon.business.view.Inventory;
import org.shop.pawn.pokemon.business.view.InventoryService;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
@Remote(InventoryService.class)
public class InventoryServiceBean implements InventoryService {

	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * Default constructor.
	 */
	public InventoryServiceBean() {

	}

	@Override
	public Inventory getAvailableInventory() {
		List<Item> list = entityManager.createQuery("SELECT i FROM Item i ", Item.class).getResultList();
		Inventory inventory = new Inventory();
		inventory.setItems(list);
//		inventory.add(new Item("mewtwo"));
//		inventory.add(new Item("pikachu"));
//		inventory.add(new Item("charmander"));
//		inventory.add(new Item("meowth"));
//		inventory.add(new Item("bulbasaur"));
//		inventory.add(new Item("squirtle"));
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
