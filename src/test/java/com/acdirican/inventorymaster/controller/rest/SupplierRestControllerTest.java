package com.acdirican.inventorymaster.controller.rest;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.acdirican.inventorymaster.model.Supplier;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SupplierRestControllerTest {
	
	private RestTemplate restTemplate;
    URI baseUri = URI.create("http://localhost:8080/rest/");
    
	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}
	
	
	@Test
	public void testGetSupplierById() {
		
		ResponseEntity<Supplier> response = restTemplate.getForEntity(baseUri +  "supplier/3"
				, Supplier.class);
		
		MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getID(), Matchers.equalTo(3));
	}
	
	@Test
	public void testGetSupplierByName() {
		
		ResponseEntity<List> response = restTemplate.getForEntity(baseUri +  "suppliers?name=E"
				, List.class);
		
		MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
		
		List<Map<String, String>> list = response.getBody();
		List<String> names = list.stream().map(e->e.get("name")).collect(Collectors.toList());
		MatcherAssert.assertThat(names, Matchers.containsInAnyOrder("Ekol Lojistik", "Zanio Warehouse"));
	
	}
	
	
	
	@Test
	public void testCreateSupplier() {
		Supplier newSupplier =  new Supplier("Armada");
		URI Location = restTemplate.postForLocation(baseUri + "/supplier", newSupplier);
		System.out.println(Location);
		ResponseEntity<Supplier> response = restTemplate.getForEntity(Location, Supplier.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
		MatcherAssert.assertThat(newSupplier.getName(), Matchers.equalTo(response.getBody().getName()));
	}

	

	
	@Test
	public void testUpdateSupplier() {
		ResponseEntity<Supplier> response = restTemplate.getForEntity(baseUri +  "supplier/3"
				, Supplier.class);
		
		MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getID(), Matchers.equalTo(3));
		
		Supplier supplier = response.getBody();
		supplier.setName("Test");
		restTemplate.put(baseUri + "supplier", supplier);
			
	    response  = restTemplate.getForEntity(baseUri +  "supplier/3"
				, Supplier.class);
	    
		MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getID(), Matchers.equalTo(3));
		MatcherAssert.assertThat(response.getBody().getName(), Matchers.equalTo("Test"));
	}

	
	@Test
	public void testDeleteSupplier() {
		//Create
		Supplier supplier =  new Supplier("Yamama");
		URI Location = restTemplate.postForLocation(baseUri + "/supplier", supplier);
		
		//Confirm
		ResponseEntity<Supplier> response = restTemplate.getForEntity(Location, Supplier.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getName(), Matchers.equalTo("Yamama"));

		//Delete
		restTemplate.delete(baseUri + "supplier/" + response.getBody().getID());
		try {
			response = restTemplate.getForEntity(Location, Supplier.class);
		}
		catch (Exception e) {
			MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(404));
		}
		
		
	}
	
	

}
