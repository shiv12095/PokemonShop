package org.shop.pawn.pokemon.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.shop.pawn.pokemon.business.view.InventoryService;
import org.shop.pawn.pokemon.model.Order;
import org.shop.pawn.pokemon.utils.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
public class OrderProcessingServiceBean {

	@PersistenceContext
	EntityManager entityManager;
	
	public OrderProcessingServiceBean() {
		// TODO Auto-generated constructor stub
	}

	public String processOrder(Order order) {
		InventoryService inventoryService = ServiceLocator.getInventoryService();
		entityManager.persist(order);
		entityManager.flush();
		/*
		 * TODO CREATE AN ADAPTER CLASS TO CONVERT LINE ITEM TO ITEM AND VICE VERSA
		 */
		// boolean quantityValid = inventoryService.validateQuantity(order.getItems());
		// boolean inventoryUpdated =
		// inventoryService.updateInventory(order.getItems());
		// if (!quantityValid || !inventoryUpdated) {
		// return "Error";
		// }
		return Integer.toString(1000 + (int) (Math.random() * ((9999 - 1000) + 1)));
	}

	public boolean validateItemAvailability(Order order) {
		/*
		 * TODO CREATE AN ADAPTER CLASS TO CONVERT LINE ITEM TO ITEM AND VICE VERSA
		 */		
		//InventoryService inventoryService = ServiceLocator.getInventoryService();
		// return inventoryService.validateQuantity(order.getItems());
		return true;
	}
}
