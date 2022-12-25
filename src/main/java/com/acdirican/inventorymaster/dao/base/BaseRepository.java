package com.acdirican.inventorymaster.dao.base;

import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.model.BaseEntity;


/**
 * Abstract base class for entity repositories
 *
 * @author Ahmet Cengizhan Dirican
 *
 */

public abstract class BaseRepository<T extends BaseEntity> {

	public abstract List<T> list();
	public abstract Optional<T> add(T object);
	public abstract Optional<T> getWidthIndex(int index);
	public abstract boolean delete(int ID);
	public abstract boolean delete(T object);
	public abstract Optional<T> getWidthID(int ID);
	public abstract T update(T object);

}
