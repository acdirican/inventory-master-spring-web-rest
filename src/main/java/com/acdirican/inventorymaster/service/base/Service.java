package com.acdirican.inventorymaster.service.base;

import java.util.List;

import com.acdirican.inventorymaster.model.BaseEntity;

public interface Service<T extends BaseEntity> extends BaseService<T>{
	public abstract List<T> find(String name);
	public abstract int deleteAll(List<Integer> id_list);
}
