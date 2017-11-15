package org.shop.pawn.pokemon.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

import org.shop.pawn.pokemon.business.model.Item;
import org.shop.pawn.pokemon.business.model.LineItem;
import org.shop.pawn.pokemon.business.view.InventoryService;
import org.shop.pawn.pokemon.model.Order;
import org.shop.pawn.pokemon.utils.LineItemAdapter;
import org.shop.pawn.pokemon.utils.ServiceLocator;

import com.chase.payment.CreditCardPayment;
import com.chase.payment.PaymentProcessorService;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
@Resource(name="jms/emailQCF", lookup="jms/emailQCF", type=ConnectionFactory.class) 
public class OrderProcessingServiceBean {

	@PersistenceContext
	private EntityManager entityManager;

	@WebServiceRef(wsdlLocation = "http://localhost:9080/ChaseBankApplication/PaymentProcessorService?wsdl")
	private PaymentProcessorService service;
	
	@Inject
	@JMSConnectionFactory("java:comp/env/jms/emailQCF")
	private JMSContext jmsContext;
	
	@Resource(lookup="jms/emailQ")
	private Queue queue;

	public OrderProcessingServiceBean() {
		// TODO Auto-generated constructor stub
	}

	public String processOrder(Order order) {
		InventoryService inventoryService = ServiceLocator.getInventoryService();
		List<Item> items = new ArrayList<Item>();
		for (LineItem listItem : order.getItems()) {
			items.add(LineItemAdapter.getItem(listItem));
		}
		CreditCardPayment ccPayment = new CreditCardPayment();
		ccPayment.setCardNumber(order.getPayment().getCreditCardNumber());
		int confirmationNumber = Integer.parseInt(service.getPaymentProcessorPort().processPayment(ccPayment));
		confirmationNumber = Math.abs(confirmationNumber);
		if (confirmationNumber < 0) {
			return "PaymentError";
		} else {
			order.getPayment().setConfirmationNumber(Integer.toString(confirmationNumber));
		}

		boolean quantityValid = inventoryService.validateQuantity(items);
		boolean inventoryUpdated = inventoryService.updateInventory(items);
		if (!quantityValid || !inventoryUpdated) {
			return "Error";
		} else {
			int orderNumber = 1000 + (int) (Math.random() * ((9999 - 1000) + 1));
			entityManager.persist(order);
			entityManager.flush();
			notifyUser(order.getCustomerEmail());
			return Integer.toString(orderNumber);			
		}
	}

	public boolean validateItemAvailability(Order order) {
		InventoryService inventoryService = ServiceLocator.getInventoryService();
		List<Item> items = new ArrayList<Item>();
		for (LineItem listItem : order.getItems()) {
			items.add(LineItemAdapter.getItem(listItem));
		}
		return inventoryService.validateQuantity(items);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	private void notifyUser(String customerEmail) {
		String message = customerEmail + ":" + "Your order was successfully submitted. You will hear from us when your order is shipped." + new Date();
		
		System.out.println("Sending message " + message);
		jmsContext.createProducer().send(queue, message);
		System.out.println("Message sent!");
	}
}
