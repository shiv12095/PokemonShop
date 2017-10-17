package org.shop.pawn.pokemon.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.shop.pawn.pokemon.business.view.InventoryService;
import org.shop.pawn.pokemon.model.Order;
import org.shop.pawn.pokemon.utils.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
public class OrderProcessingServiceBean {

    /**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
        // TODO Auto-generated constructor stub
    }

    public String processOrder(Order order) {
    	InventoryService inventoryService = ServiceLocator.getInventoryService();
    	boolean quantityValid = inventoryService.validateQuantity(order.getItems());
    	boolean inventoryUpdated = inventoryService.updateInventory(order.getItems());
    	if (!quantityValid || !inventoryUpdated) {
    		return "Error";
    	}
    	return Integer.toString(1000 + (int)(Math.random() * ((9999 - 1000) + 1)));
    }
    
    public boolean validateItemAvailability(Order order) {
    	InventoryService inventoryService = ServiceLocator.getInventoryService();
    	return inventoryService.validateQuantity(order.getItems());
    }
}
