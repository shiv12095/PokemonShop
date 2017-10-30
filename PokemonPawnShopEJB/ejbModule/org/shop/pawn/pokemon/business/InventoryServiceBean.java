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
	private EntityManager entityManager;

	static String QUERY = "SELECT i FROM Item i ";

	/**
	 * Default constructor.
	 */
	public InventoryServiceBean() {

	}

	@Override
	public Inventory getAvailableInventory() {
		List<Item> list = getEntityManager().createQuery(QUERY, Item.class).getResultList();
		Inventory inventory = new Inventory();
		inventory.setItems(list);
		return inventory;
	}

	@Override
	public boolean validateQuantity(Collection<Item> items) {
		boolean isValid = true;
		Inventory inventory = getAvailableInventory();
		
		for (Item userItem : items) {
			for (Item inventoryItem : inventory.getItems()) {
				if (userItem.getName().equals(inventoryItem.getName())) {
					if (Integer.parseInt(userItem.getQuantity()) > Integer.parseInt(inventoryItem.getQuantity())) {
						isValid = false;
						break;
					}
				}
			}
		}
		
		
		return isValid;
	}

	@Override
	public boolean updateInventory(Collection<Item> items) {
		for(Item item : items) {
			Item tempItem = entityManager.find(Item.class, item.getId());
			int newQuantity = Integer.parseInt(tempItem.getQuantity()) - Integer.parseInt(item.getQuantity());
			if(newQuantity < 0) {
				return false;
			}
			tempItem.setQuantity(Integer.toString(newQuantity));
			entityManager.persist(item);
		}
		entityManager.flush();
		return true;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
