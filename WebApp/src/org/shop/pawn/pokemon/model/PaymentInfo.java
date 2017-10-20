package org.shop.pawn.pokemon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAYMENT_INFO")
public class PaymentInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")	
	private int id;

	@Column(name="CREDIT_CARD_NUMBER")
	private String creditCardNumber;

	@Column(name="EXPIRATION_DATE")
	private String expirationDate;

	@Column(name="CARD_HOLDER_NAME")
	private String cardHolderName;

	@Column(name="CVV")
	private String cvvCode;

	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	public String getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
	
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	public String getCvvCode() {
		return cvvCode;
	}
	
	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}
	
	public boolean isValid() {
//		if(this.creditCardNumber == null || this.creditCardNumber.isEmpty()) {
//			return false;
//		}
//		if(this.cardHolderName == null || this.cardHolderName.isEmpty()) {
//			return false;
//		}	
//		if(this.expirationDate == null || this.expirationDate.isEmpty()) {
//			return false;
//		}
//		if(this.cvvCode == null || this.cvvCode.isEmpty()) {
//			return false;
//		}
//		if(this.creditCardNumber.length() != 16) {
//			return false;
//		}
		return true;		
	}
}
