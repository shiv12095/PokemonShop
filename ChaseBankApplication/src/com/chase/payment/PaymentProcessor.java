package com.chase.payment;

import java.util.Random;

import com.chase.model.CreditCardPayment;

public class PaymentProcessor {

	private Random random = new Random();

	public String ping() {
		return "alive";
	}

	public String processPayment(CreditCardPayment cardPayment) {
		return Integer.toString(random.nextInt());
	}

}
