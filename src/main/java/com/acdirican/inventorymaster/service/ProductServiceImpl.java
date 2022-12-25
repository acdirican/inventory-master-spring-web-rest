package com.acdirican.inventorymaster.service;

import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.dao.base.ProductRepository;
import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.service.base.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProductServiceImpl implements ProductService {
	
	
	private ProductRepository productRepository;

	public ProductServiceImpl() {}
	
public ProductRepository getProductRepository() {
	return productRepository;
}

@Autowired
public void setProductRepository(ProductRepository productRepository) {
	this.productRepository = productRepository;
}

	@Override
	public List<Product> list() {
		return productRepository.list();
	}

	@Override
	public Optional<Product> add(Product object) {
		return productRepository.add(object);
	}

	@Override
	public Optional<Product> getWidthIndex(int index) {
		return productRepository.getWidthIndex(index);
	}

	@Override
	public boolean delete(int ID) {
		return productRepository.delete(ID);
	}

	@Override
	public boolean delete(Product object) {
		return productRepository.delete(object);
	}

	@Override
	public Optional<Product> getWidthID(int ID) {
		return productRepository.getWidthID(ID);
	}

	@Override
	public Product update(Product object) {
		return productRepository.update(object);
	}

	@Override
	public List<Product> find(String name) {
		return productRepository.find(name);
	}

	@Override
	public int deleteAll(List<Integer> id_list) {
		return productRepository.deleteAll(id_list);
	}

	@Override
	public List<Product> listMoreThan(double quantity) {
		return productRepository.listMoreThan(quantity);
	}

	@Override
	public List<Product> listLessThan(double quantity) {
		return productRepository.listLessThan(quantity);
	}

	@Override
	public List<Product> listEquals(double quantity) {
		return productRepository.listEquals(quantity);
	}

	@Override
	public List<Product> listDepleteds() {
		return productRepository.listDepleteds();
	}

	@Override
	public int increaseInvetory(int ID, double quantity) {
		return productRepository.increaseInvetory(ID, quantity);
	}

	@Override
	public boolean increaseInvetory(Product product, double quantity) {
		return productRepository.decreaseInvetory(product, quantity);
	}

	@Override
	public boolean decreaseInvetory(Product product, double quantity) {
		return productRepository.decreaseInvetory(product, quantity);
	}


}
