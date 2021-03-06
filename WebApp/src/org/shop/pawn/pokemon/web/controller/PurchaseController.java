package org.shop.pawn.pokemon.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shop.pawn.pokemon.business.OrderProcessingServiceBean;
import org.shop.pawn.pokemon.business.model.LineItem;
import org.shop.pawn.pokemon.business.view.Inventory;
import org.shop.pawn.pokemon.business.view.InventoryService;
import org.shop.pawn.pokemon.model.Order;
import org.shop.pawn.pokemon.model.PaymentInfo;
import org.shop.pawn.pokemon.model.ShippingInfo;
import org.shop.pawn.pokemon.utils.ServiceLocator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	private InventoryService inventoryService = ServiceLocator.getInventoryService();
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryPage(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Inventory inventory = inventoryService.getAvailableInventory();
		request.setAttribute("inventory", inventory);
		return "form/OrderEntryForm";
	}
	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) {
		OrderProcessingServiceBean orderProcessingService = ServiceLocator.getOrderProcessingService();
		List<LineItem> tempList = new ArrayList<LineItem>();
		for(LineItem listItem : order.getItems()) {
			if(listItem.getQuantity() > 0) {
				listItem.setItemId(listItem.getId());
				listItem.setId(null);
				tempList.add(listItem);
			}
		}
		order.setItems(tempList);		
		boolean isValid = orderProcessingService.validateItemAvailability(order);
		if (isValid) {
			request.getSession().setAttribute("order", order);
			request.getSession().setAttribute("error", "f");
			return "redirect:/purchase/paymentEntry";
		} else {
			request.getSession().setAttribute("error", "t");
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
		for(LineItem item : order.getItems()) {
			if(item.getQuantity() > 0) {
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
		stringBuidler.append("Email Address : " + shippingInfo.getCustomerEmail() + "\n");
		
		request.getSession().setAttribute("shippingInfo", stringBuidler.toString());
		
		order.setCustomerName(paymentInfo.getCardHolderName());
		order.setPayment(paymentInfo);
		order.setShipping(shippingInfo);
		order.setCustomerEmail(shippingInfo.getCustomerEmail());
		request.getSession().setAttribute("order", order);
		
		return "ViewOrder";
	}	

	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpServletRequest request) {
		OrderProcessingServiceBean orderProcessingService = ServiceLocator.getOrderProcessingService();
		Order orderFromRequest = (Order) request.getSession().getAttribute("order");
		String orderNumber = orderProcessingService.processOrder(orderFromRequest);
		request.getSession().setAttribute("orderNumber", orderNumber);
		return "OrderConfirmation";
	}

}
