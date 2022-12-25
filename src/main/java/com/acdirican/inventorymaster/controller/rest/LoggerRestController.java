package com.acdirican.inventorymaster.controller.rest;

import java.util.List;
import java.util.Optional;

import com.acdirican.inventorymaster.model.Log;
import com.acdirican.inventorymaster.service.base.LoggerService;

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
public class LoggerRestController {
	
	@Autowired
	private LoggerService loggerService;

	@RequestMapping(method=RequestMethod.GET, value="/logs")
	public ResponseEntity<List<Log>> getLogs() {
		try {
			List<Log> logs = loggerService.list();
			return ResponseEntity.ok(logs);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/logs/{id}")
	public ResponseEntity<Log> getLogs(@PathVariable("id") Integer id) {
		try {
			Optional<Log> log = loggerService.getWidthID(id);
			if (log.isPresent()) {
				return ResponseEntity.ok(log.get());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/logs_by_product")
	public ResponseEntity<List<Log>> getLogsByProduct(@RequestParam("product") Integer productID) {
		try {
			List<Log> logs = loggerService.listByProduct(productID);
			return ResponseEntity.ok(logs);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}