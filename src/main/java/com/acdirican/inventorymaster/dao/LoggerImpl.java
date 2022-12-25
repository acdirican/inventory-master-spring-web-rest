package com.acdirican.inventorymaster.dao;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.acdirican.inventorymaster.dao.base.LoggerRepository;
import com.acdirican.inventorymaster.exceptions.EntityNotFoundException;
import com.acdirican.inventorymaster.model.Log;

import org.springframework.stereotype.Repository;

/**
 * Looger class to keep track inventory updates using Log entity.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */

@Repository("loggerRepository")
public class LoggerImpl extends LoggerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public LoggerImpl() {}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Log> list() {
		return entityManager.createQuery("from Log", Log.class).getResultList();
	}
	
	public List<Log> listByProduct(int productID) {
		return entityManager.createNamedQuery("Log.filterByProductID", Log.class)
				.setParameter("id", productID)
				.getResultList();
	}
	
	public Optional<Log> add(Log log) {
		if (log != null) {
			try {
				
				entityManager.persist(log);
				
				return Optional.of(log);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Optional.empty();
	}

	/**
	 * Finds the Log matching the given index
	 * 
	 * @param index
	 * @return
	 */
	public Optional<Log> getWidthIndex(int index) {
		//? error prone
		return Optional.of(list().get(index));
	}

	public boolean delete(int ID) {
		Optional<Log> suOptional=getWidthID(ID);
		if (suOptional.isPresent()) {
			return delete(suOptional.get());
		}
		return false;
	}

	public boolean delete(Log log) {
		if(log == null) {
			return false;
		}
		
		entityManager.remove(log);
		
		return true;
	}

	public Optional<Log> getWidthID(int ID) {
		Log log =  entityManager.find(Log.class, ID);
		return log != null 
				? Optional.of(log)
				: Optional.empty();
	}

	public Log update(Log log) {
		if(log == null) {
			throw new NullPointerException();
		}
		
		return entityManager.merge(log);
	}

	

}
