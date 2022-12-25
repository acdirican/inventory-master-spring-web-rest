package com.acdirican.inventorymaster.service.base;

import java.util.List;

import com.acdirican.inventorymaster.model.Log;

public interface LoggerService extends BaseService<Log> {
	public abstract List<Log> listByProduct(int productID);
}
