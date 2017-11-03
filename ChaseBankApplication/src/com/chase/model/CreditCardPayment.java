package com.chase.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CreditCardPayment implements Serializable {

	private String cardNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
}
