package com.acdirican.inventorymaster.dao.base;

import java.util.List;

import com.acdirican.inventorymaster.model.BaseEntity;

public abstract class Respository<T extends BaseEntity> extends BaseRepository<T> {
	public abstract List<T> find(String name);
	/* Batch operation */
	public abstract int deleteAll(List<Integer> id_list);
}
