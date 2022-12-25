package com.acdirican.inventorymaster.service;


import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.dao.base.SupplierRepository;
import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.service.base.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Looger class to keep track inventory updates using Supplier entity.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;

	public SupplierServiceImpl() {}
	
	public SupplierRepository getSupplierRepository() {
		return supplierRepository;
	}
	public void setSupplierRepository(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public List<Supplier> list() {
		return supplierRepository.list();
	}

	@Override
	public Optional<Supplier> add(Supplier object) {
		return supplierRepository.add(object);
	}

	@Override
	public Optional<Supplier> getWidthIndex(int index) {
		return supplierRepository.getWidthIndex(index);
	}

	@Override
	public boolean delete(int ID) {
		return supplierRepository.delete(ID);
	}

	@Override
	public boolean delete(Supplier object) {
		return supplierRepository.delete(object);
	}

	@Override
	public Optional<Supplier> getWidthID(int ID) {
		return supplierRepository.getWidthID(ID);
	}

	@Override
	public Supplier update(Supplier object) {
		return supplierRepository.update(object);
	}

	@Override
	public List<Supplier> find(String name) {
		return supplierRepository.find(name);
	}

	@Override
	public int deleteAll(List<Integer> id_list) {
		return supplierRepository.deleteAll(id_list);
	}


	

}
