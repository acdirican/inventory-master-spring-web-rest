package com.acdirican.inventorymaster.controller.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.service.base.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("rest")
public class SupplierRestController {
	
	@Autowired
	private SupplierService supplierService;

	@RequestMapping(method=RequestMethod.GET, value="/suppliers")
	public ResponseEntity<List<Supplier>> getSuppliers() {
		try {
			List<Supplier> products = supplierService.list();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/supplier", params = {"name"})
	public ResponseEntity<List<Supplier>> getSuppliers(@RequestParam("name") String name) {
		try {
			List<Supplier> products = supplierService.find(name);
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/supplier/{id}")
	public ResponseEntity<Supplier> getSupplier(@PathVariable("id") Integer id) {
		try {
			
			Optional<Supplier> prev = supplierService.getWidthID(id);
			
			if (prev.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(prev.get());
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}


	@RequestMapping(method=RequestMethod.POST, value="/supplier")
	public ResponseEntity<URI> createSupplier(@RequestBody Supplier supplier) {
		try {
			Optional<Supplier> created = supplierService.add(supplier);
			if (created.isPresent()) {
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(created.get().getID()).toUri();
				return ResponseEntity.created(uri).build();
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/supplier")
	public ResponseEntity<?> updateSupplier(@RequestBody Supplier supplier) {
		try {
			
			Optional<Supplier> prev = supplierService.getWidthID(supplier.getID());
			
			if (prev.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			supplierService.update(prev.get());
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/supplier/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable("id") Integer id) {
		try {
			return supplierService.delete(id) 
					? ResponseEntity.ok().build()
					: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}