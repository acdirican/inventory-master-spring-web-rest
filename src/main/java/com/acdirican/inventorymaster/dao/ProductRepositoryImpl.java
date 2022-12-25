package com.acdirican.inventorymaster.dao;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.acdirican.inventorymaster.dao.base.LoggerRepository;
import com.acdirican.inventorymaster.dao.base.ProductRepository;
import com.acdirican.inventorymaster.model.InventoryMovement;
import com.acdirican.inventorymaster.model.Log;
import com.acdirican.inventorymaster.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Repository class for the product entity.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */
@Repository("productRepository")
public class ProductRepositoryImpl extends ProductRepository {
	
	@Autowired
	private LoggerRepository logger;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public ProductRepositoryImpl() {}


	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Product> list() {
		return entityManager.createQuery("from Product", Product.class).getResultList();
	}

	/* NamedQuery */
	public List<Product> listMoreThan(double quantity) {
		return entityManager.createNamedQuery("Product.findMoreThan", Product.class).setParameter("quantity", quantity)
				.getResultList();
	}

	/* NamedQuery */
	public List<Product> listLessThan(double quantity) {
		return entityManager.createNamedQuery("Product.findLessThan", Product.class).setParameter("quantity", quantity)
				.getResultList();
	}

	/**
	 * List product whose quantities are equals to the given value using a stored
	 * procedure
	 * 
	 */
	public List<Product> listEquals(double quantity) {
		return entityManager.createNamedStoredProcedureQuery("Product.filterByQuantity")
				.setParameter("quantity", quantity)
				.getResultList();
		
		/* Alternative
		return entityManager.createQuery("select p from Product p WHERE p.quantity = :quantity", Product.class)
				.setParameter("quantity", quantity)
				.getResultList();
		*/
	}

	/**
	 * List delpeted product using a stored procedure
	 * 
	 */
	public List<Product> listDepleteds() {
		//return entityManager.createStoredProcedureQuery("get_depleted_products", Product.class).getResultList();
		return entityManager.createNamedStoredProcedureQuery("Product.getDepleteds")
				.getResultList();
	}

	public Optional<Product> add(Product product) {
		 try {

            entityManager.persist(product);
            logger.add(new Log(product));
            return Optional.of(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
	}

	/* ? Do using JPA */
	public Optional<Product> getWidthIndex(int i) {
		//? error prone
		return Optional.of(list().get(i));
	}
	
	public boolean delete(int ID) {
		Optional<Product> product = getWidthID(ID);
		if (product.isPresent()) {
			return delete(product.get());
		}
		return false;
	}
	
	public boolean delete(Product product) {
		if (product == null) {
			return false;
		}
		
		entityManager.remove(product);
		
		return true;
	}

	public Optional<Product> getWidthID(int ID) {
		Product product = entityManager.find(Product.class, ID);
		return product != null 
				? Optional.of(product)
				: Optional.empty();
	}

	public List<Product> find(String name) {
		return entityManager.createNamedQuery("Product.findByName", Product.class)
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}

	public Product update(Product product) {
		if(product == null) {
			throw new NullPointerException();
		}
		Product updated = entityManager.merge(product);
		logger.add(new Log(product, InventoryMovement.UPDATE));
		return updated;
	}

	/* Batch operation */
	public int deleteAll(List<Integer> id_list) {
		StringJoiner ids =  new StringJoiner(",");
		for (int ID : id_list) {
			 ids.add(String.valueOf(ID));
		}
		String sql =  "delete from Product p where p.ID IN (" + ids.toString() + ")" ;
		//System.out.println(sql);
		
		int result = entityManager.createQuery(sql).executeUpdate();
		
		return result;
	}

	/*Using named query*/
	public int increaseInvetory(int ID, double quantity) {
		;
		int result = entityManager.createNamedQuery("Product.increaseInvetory").setParameter("id", ID)
				.setParameter("quantity", quantity).executeUpdate();
		
		if(result>0) {
			logger.add(new Log(quantity, getWidthID(ID).get(), InventoryMovement.INCREASE));
		}
		return result;
	}

	/*Using the entity*/
	public boolean increaseInvetory(Product product, double quantity) {
		if (product== null && quantity>0) {
			return false;
		}
		double previousValue = product.getQuantity();
		
		product.setQuantity(product.getQuantity() + quantity);
		entityManager.merge(product);
		
		
		
		if(product.getQuantity() != previousValue) {
			logger.add(new Log(quantity, product, InventoryMovement.INCREASE));
		}
		return true;
	}

	public boolean decreaseInvetory(Product product, double quantity) {
		if (product== null && quantity>0) {
			return false;
		}
		double previousValue = product.getQuantity();
		
		product.setQuantity(product.getQuantity() - quantity);
		entityManager.merge(product);
		
		if(product.getQuantity() != previousValue) {
			logger.add(new Log(quantity, product, InventoryMovement.DECREASE));
		}
		return true;
	}



}
