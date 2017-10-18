package org.shop.pawn.pokemon.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shop.pawn.pokemon.business.OrderProcessingServiceBean;
import org.shop.pawn.pokemon.business.model.Item;
import org.shop.pawn.pokemon.business.view.Inventory;
import org.shop.pawn.pokemon.business.view.InventoryService;
import org.shop.pawn.pokemon.model.Order;
import org.shop.pawn.pokemon.model.PaymentInfo;
import org.shop.pawn.pokemon.model.ShippingInfo;
import org.shop.pawn.pokemon.utils.ServiceLocator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	private InventoryService inventoryService = ServiceLocator.getInventoryService();
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryPage(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Inventory inventory = inventoryService.getAvailableInventory();
		Order order = new Order();
		order.setItems(inventory.getItems());
		
		request.setAttribute("order", order);
		request.setAttribute("inventory", inventory);		
		return "form/OrderEntryForm";
	}
	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		OrderProcessingServiceBean orderProcessingService = ServiceLocator.getOrderProcessingService();
		boolean isValid = orderProcessingService.validateItemAvailability(order);
		if (isValid) {
			request.getSession().setAttribute("order", order);
			return "redirect:/purchase/paymentEntry";
		}else {
			// TODO Render message asking to redo item quantities
			return "redirect:/purchase";
		}
	}
	
	@RequestMapping(value = "/shippingEntry", method = RequestMethod.GET)
	public String viewShippingEntryPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("shippingInfo", new ShippingInfo());
		return "form/ShippingEntryForm";
	}
	
	@RequestMapping(path = "/submitShipping", method = RequestMethod.POST)
	public String submitShippingInfo(@ModelAttribute("shippingInfo") ShippingInfo shippingInfo, HttpServletRequest request) {
		if(shippingInfo.isValid()) {
			request.getSession().setAttribute("shippingInfo", shippingInfo);
			return "redirect:/purchase/viewOrder";			
		}else {
			return "redirect:/purchase/shippingEntry";
		}
	}
	
	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String viewPaymentEntryPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PaymentInfo paymentInfo = new PaymentInfo();
		request.setAttribute("paymentInfo", paymentInfo);
		return "form/PaymentEntryForm";
	}	
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo, HttpServletRequest request) {
		if (paymentInfo.isValid()) {
			request.getSession().setAttribute("paymentInfo", paymentInfo);
			return "redirect:/purchase/shippingEntry";
		} else {
			return "redirect:/purchase/paymentEntry";
		}
	}
	
	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrderPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuilder stringBuidler = new StringBuilder();
		Order order = (Order)request.getSession().getAttribute("order");
		for(Item item : order.getItems()) {
			if(Integer.parseInt(item.getQuantity()) > 0) {
				stringBuidler.append(item.getName() + " " + item.getQuantity() + "\n");
			}
		}
		request.getSession().setAttribute("orderInfo", stringBuidler.toString());
		
		PaymentInfo paymentInfo = (PaymentInfo)request.getSession().getAttribute("paymentInfo");
		stringBuidler = new StringBuilder();
		stringBuidler.append("Card Holder name : " + paymentInfo.getCardHolderName() + "\n");
		stringBuidler.append("Card expiration date : " + paymentInfo.getExpirationDate() + "\n");
		
		request.getSession().setAttribute("paymentInfo", stringBuidler.toString());
		
		ShippingInfo shippingInfo = (ShippingInfo)request.getSession().getAttribute("shippingInfo");
		stringBuidler = new StringBuilder();
		stringBuidler.append("Address 1 : " + shippingInfo.getAddressLine1() + "\n");
		stringBuidler.append("Address 2 : " + shippingInfo.getAddressLine2()+ "\n");
		stringBuidler.append("City : " + shippingInfo.getCity() + "\n");
		stringBuidler.append("State : " + shippingInfo.getState() + "\n");
		stringBuidler.append("Zipcode : " + shippingInfo.getZipCode() + "\n");	
		
		request.getSession().setAttribute("shippingInfo", stringBuidler.toString());
		
		return "ViewOrder";
	}	

	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(@ModelAttribute("order") Order order, HttpServletRequest request) {
		OrderProcessingServiceBean orderProcessingService = ServiceLocator.getOrderProcessingService();
		String orderNumber = orderProcessingService.processOrder(order);
		request.getSession().setAttribute("orderNumber", orderNumber);
		return "OrderConfirmation";
	}

}
