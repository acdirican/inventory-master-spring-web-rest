package com.acdirican.inventorymaster.controller.rest;

import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.service.base.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("rest")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping(method=RequestMethod.GET, value="/products")
	public ResponseEntity<List<Product>> getProducts() {
		try {
			List<Product> products = productService.list();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/products",params =  {"name"} )
	public ResponseEntity<List<Product>> getProduct(@RequestParam("name") String name ){
		try {
			List<Product> products = productService.find(name);
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/product/{id}" )
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
		try {
			Optional<Product> product = productService.getWidthID(id);
			if (product.isPresent()) {
				return ResponseEntity.ok(product.get());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	


}