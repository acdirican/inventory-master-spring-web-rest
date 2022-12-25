package com.acdirican.inventorymaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Starter class of the project.
 * 
 * @author Ahmet Cengizhan Dirican
 *
 */ 
@SpringBootApplication
@ServletComponentScan
public class Main {
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}


}
