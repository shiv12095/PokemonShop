package org.shop.pawn.pokemon.utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.shop.pawn.pokemon.business.OrderProcessingServiceBean;
import org.shop.pawn.pokemon.business.view.InventoryService;

public class ServiceLocator {
	
	public static OrderProcessingServiceBean getOrderProcessingService() {
		try {
			return (OrderProcessingServiceBean) InitialContext.doLookup("java:module/OrderProcessingServiceBean!org.shop.pawn.pokemon.business.OrderProcessingServiceBean");
		} catch (NamingException ne) {
			throw new RuntimeException(ne);
		}
	}
	
	public static InventoryService getInventoryService() {		
		try {
			return (InventoryService) InitialContext.doLookup("java:global/PokemonPawnShopEJBEAR/PokemonPawnShopEJB/InventoryServiceBean!org.shop.pawn.pokemon.business.view.InventoryService");
		} catch (NamingException ne) {
			throw new RuntimeException(ne);
		}
	}

}
