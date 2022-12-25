package com.acdirican.inventorymaster.dao.base;

import java.util.List;

import com.acdirican.inventorymaster.model.Product;

public abstract class ProductRepository extends Respository<Product> {
	
	/* NamedQuery */
	public abstract List<Product> listMoreThan(double quantity);

	/* NamedQuery */
	public abstract List<Product> listLessThan(double quantity);

	/**
	 * List product whose quantities are equals to the given value using a stored
	 * procedure
	 * 
	 */
	public abstract List<Product> listEquals(double quantity);

	/**
	 * List delpeted product using a stored procedure
	 * 
	 */
	public abstract List<Product> listDepleteds();

	/* Using named query */
	public abstract int increaseInvetory(int ID, double quantity);

	/* Using the entity */
	public abstract boolean increaseInvetory(Product product, double quantity);

	public abstract boolean decreaseInvetory(Product product, double quantity);

}
