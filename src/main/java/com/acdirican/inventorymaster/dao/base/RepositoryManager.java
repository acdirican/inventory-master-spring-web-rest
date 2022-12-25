package com.acdirican.inventorymaster.dao.base;


import java.util.Optional;

import com.acdirican.inventorymaster.dao.LoggerImpl;
import com.acdirican.inventorymaster.dao.ProductRepositoryImpl;
import com.acdirican.inventorymaster.dao.SupplierRepositoryImpl;
import com.acdirican.inventorymaster.model.Supplier;

/**
 * Factory to create entity repositories {@link ProductRepositoryImpl} and {@link SupplierRepositoryImpl}, and to connect to the database;
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
public abstract  class RepositoryManager {
	protected static final String ERROR = "Database error!";
	
	protected static ProductRepository productRepository = new ProductRepositoryImpl();
	protected static SupplierRepository supplierRepository = new SupplierRepositoryImpl();
	protected static LoggerRepository logger =  new LoggerImpl();
	
	
	public static ProductRepository getProductRepository() {
		return productRepository;
	}
	
	public static SupplierRepository getSupplierRepository() {
		return supplierRepository;
	}
	
	public static LoggerRepository getLogger() {
		return logger;
	}

	public static Optional<Supplier> findSupplier(int ID) {
		return supplierRepository.getWidthID(ID);
	}
}
