package com.acdirican.inventorymaster.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name ="supplier")
@NamedQueries({
		@NamedQuery(name = "Supplier.findByName", query = "SELECt s FROM Supplier s WHERE s.name LIKE :name"),
		@NamedQuery(name = "Supplier.listProducts", query ="SELECT s FROM Supplier s WHERE s.ID = :id")} )
@SecondaryTable(name = "contact_person", pkJoinColumns = @PrimaryKeyJoinColumn(name="SupplierID"))
public class Supplier extends BaseEntity {
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Product> products;
	
	@Embedded
    @AttributeOverrides({
        @AttributeOverride(
            name = "phone",
            column = @Column( name = "phone" )
        ),
        @AttributeOverride(
            name = "address",
            column = @Column( name = "address" )
        )
    })
	private Contact contact;
	/*
	@Embedded
	@Column(name = "contactPerson", table = "contact_person")
	private ContactPerson contactPerson;
	*/
	public Supplier() {}
	
	public Supplier(String name, Contact contact) {
		super();
		this.name = name;
		this.contact = contact;
	}
	
	public Supplier(String name,String phone, String address, String contactFirstName, String contactPersonLastName) {
		super();
		this.name = name;
		this.contact = new Contact(phone, address);
		//this.contactPerson = new ContactPerson(contactFirstName, contactPersonLastName);
	}
	
	public Supplier(String name) {
		this(name, null);
	}
	public Supplier(int ID) {
		this.ID=ID;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
/*
	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}
	*/

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "* Supplier:\n"
					+ "ID=" + ID + ", name=" + name + "\n" 
					+ (contact !=null ? contact.toString() : "")  + "\n";
					//+ (contactPerson !=null ? contactPerson.toString() : "")//;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
}
