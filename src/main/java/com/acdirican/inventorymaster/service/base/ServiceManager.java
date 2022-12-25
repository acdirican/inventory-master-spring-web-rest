package com.acdirican.inventorymaster.service.base;

import java.util.Optional;

import com.acdirican.inventorymaster.model.Supplier;

public interface ServiceManager {
	public ProductService getProductService();
	public SupplierService getSupplierService();
	public LoggerService getLoggerService();
	public Optional<Supplier> findSupplier(int ID);
	public String metaData();
}
