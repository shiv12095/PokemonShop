package org.shop.pawn.pokemon.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.shop.pawn.pokemon.model.Order;

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
    		return "";
    }
}
