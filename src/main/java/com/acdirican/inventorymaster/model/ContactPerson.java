package com.acdirican.inventorymaster.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.SecondaryTable;

@Embeddable
@SecondaryTable(name = "contact_person")
public class ContactPerson {
	@Column(name = "FirstName", table = "contact_person")
	private String firstName;
	@Column(name = "LastName", table = "contact_person")
	private String lastName;
	
	public ContactPerson() {}
	
	public ContactPerson(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "ContactPerson [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
