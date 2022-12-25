package com.acdirican.inventorymaster.dao.base;

import java.util.List;

import com.acdirican.inventorymaster.model.Log;

/**
 * Abstract base class for entity repositories
 *
 * @author Ahmet Cengizhan Dirican
 *
 */
public abstract class LoggerRepository extends BaseRepository<Log> {

	public abstract List<Log> listByProduct(int productID);


}
