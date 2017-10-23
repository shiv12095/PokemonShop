package org.shop.pawn.pokemon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")

@Entity
@Table(name="SHIPPING_INFO")
public class ShippingInfo {
	
	public ShippingInfo() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	@Column(name="ADDRESS_LINE_1")
	private String addressLine1;

	@Column(name="ADDRESS_LINE_2")
	private String addressLine2;

	@Column(name="CITY")
	private String city;

	@Column(name="STATE")
	private String state;

	@Column(name="ZIP_CODE")
	private String zipCode;

	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public boolean isValid() {
		if(this.addressLine1 == null || this.addressLine1.isEmpty()) {
			return false;
		}
		if(this.addressLine2 == null || this.addressLine2.isEmpty()) {
			return false;
		}	
		if(this.city == null || this.city.isEmpty()) {
			return false;
		}
		if(this.state == null || this.state.isEmpty()) {
			return false;
		}
		if(this.zipCode == null || this.zipCode.isEmpty()) {
			return false;
		}
		return true;		
	}
}
