package com.acdirican.inventorymaster.dao;


import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.acdirican.inventorymaster.dao.base.SupplierRepository;
import com.acdirican.inventorymaster.exceptions.EntityNotFoundException;
import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.model.Supplier;

import org.springframework.stereotype.Repository;


/**
 * Repository class for the supplier entity.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
@Repository("supplierRepository")
public class SupplierRepositoryImpl extends SupplierRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public SupplierRepositoryImpl() {}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public List<Supplier> list() {
		return entityManager.createQuery("from Supplier", Supplier.class).getResultList();
	}

	public Optional<Supplier> add(Supplier supplier) {
		if (supplier != null) {
			try {
				  Supplier merged = entityManager.merge(supplier);
				  entityManager.persist(merged);
				return Optional.of(merged);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Optional.empty();
	}

	/**
	 * Finds the Supplier matching the given index
	 * 
	 * @param index
	 * @return
	 */
	public Optional<Supplier> getWidthIndex(int index) {
		//? error prone
		return Optional.of(list().get(index));
	}

	public boolean delete(int ID) {
		Optional<Supplier> suOptional=getWidthID(ID);
		if (suOptional.isPresent()) {
			return delete(suOptional.get());
		}
		return false;
	}

	public boolean delete(Supplier supplier) {
		if(supplier == null) {
			return false;
		}
		Supplier merged = entityManager.merge(supplier);
		entityManager.remove(merged);
		
		return true;
	}

	public Optional<Supplier> getWidthID(int ID) {
		Supplier supplier =  entityManager.find(Supplier.class, ID);
		return supplier != null 
				? Optional.of(supplier)
				: Optional.empty();
	}

	/* Prepared Statement */
	public List<Product> getProducts(Supplier supplier) {
		 return entityManager.createNamedQuery("Supplier.listProducts", Product.class)
				.setParameter("id", supplier.getID()) 
				.getResultList();
	}

	public Supplier update(Supplier supplier) {
		if(supplier == null) {
			throw new NullPointerException("Supplier is null!");
		}
		
		return entityManager.merge(supplier);
	}

	public List<Supplier> find(String name) {
		return entityManager.createNamedQuery("Supplier.findByName", Supplier.class)
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}

	/* Batch operation */
	public int deleteAll(List<Integer> id_list) {
		StringJoiner ids =  new StringJoiner(",");
		for (int ID : id_list) {
			 ids.add(String.valueOf(ID));
		}
		String sql =  "delete from Supplier s where s.ID IN (" + ids.toString() + ")" ;
		//System.out.println(sql);
		
		int result = entityManager.createQuery(sql).executeUpdate();
		
		return result;
	}
}
