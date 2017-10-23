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

	static String QUERY = "SELECT i FROM Item i ";

	/**
	 * Default constructor.
	 */
	public InventoryServiceBean() {

	}

	@Override
	public Inventory getAvailableInventory() {
		List<Item> list = entityManager.createQuery(QUERY, Item.class).getResultList();
		Inventory inventory = new Inventory();
		inventory.setItems(list);
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
