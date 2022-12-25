package com.acdirican.inventorymaster.model;


import javax.persistence.Embeddable;

@Embeddable
public class Contact {
	private String phone;
	private String address;
	
	public Contact() {}
	
	public Contact(String phone, String address) {
		super();
		this.phone = phone;
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Contact [phone=" + phone + ", address=" + address + "]";
	}
	
	
	
	
	
}
