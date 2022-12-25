package com.acdirican.inventorymaster.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "log")
@NamedQueries({
	@NamedQuery(name="Log.filterByProductID",query= "select l from Log l where l.product.id = :id")	
})
public class Log extends BaseEntity{
	
	@Column(name="quantity", nullable = false)
	private Double quantity;
	
	@Column(name="time", nullable = false)
	private LocalDateTime time; 
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ProductID")
	private Product product;

	@Enumerated(EnumType.STRING)
	@Column(name="type", nullable = false)
	private InventoryMovement type;
	
	public Log() {}
	
	//New product
	public Log( Product product) {
		this.quantity = product.getQuantity();
		this.time = LocalDateTime.now();
		this.product = product;
		this.type = InventoryMovement.FIRST;
	}

	//Increase of deacrese inventory
	public Log(double quantity, Product product, InventoryMovement type) {
		this.quantity = quantity;
		this.time = LocalDateTime.now();
		this.product = product;
		this.type = type;
	}


	//Update product
	public Log(Product product, InventoryMovement type) {
		this(product);
		this.type = InventoryMovement.UPDATE;
	}

	

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Log [ID=" + ID + ", quantity=" + quantity + ", time=" + time + ", product=" + product.getName() + "]";
	}

	public InventoryMovement getType() {
		return type;
	}
	
	
	
	
	
}
