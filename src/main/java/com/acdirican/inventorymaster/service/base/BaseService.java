package com.acdirican.inventorymaster.service.base;

import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.model.BaseEntity;
import com.acdirican.inventorymaster.model.Log;

public interface BaseService<T extends BaseEntity> {
	List<T> list();
	Optional<T> add(T object);
	Optional<T> getWidthIndex(int index);
	boolean delete(int ID);
	boolean delete(T object);
	Optional<T> getWidthID(int ID);
	T update(T object);
}
