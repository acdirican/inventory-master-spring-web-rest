package com.acdirican.inventorymaster.service.base;

import java.util.List;

import com.acdirican.inventorymaster.model.Product;

public interface ProductService extends Service<Product> {

	public abstract List<Product> listMoreThan(double quantity);

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

	public abstract int increaseInvetory(int ID, double quantity);

	public abstract boolean increaseInvetory(Product product, double quantity);

	public abstract boolean decreaseInvetory(Product product, double quantity);
}
