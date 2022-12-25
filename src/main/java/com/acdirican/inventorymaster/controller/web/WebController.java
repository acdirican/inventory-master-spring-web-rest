package com.acdirican.inventorymaster.controller.web;

import java.util.Date;
import java.util.Random;

import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.service.base.LoggerService;
import com.acdirican.inventorymaster.service.base.ProductService;
import com.acdirican.inventorymaster.service.base.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Main web controller class including general requests
 */
@Controller
public class WebController {
	
	@Autowired(required = false)
	private ProductService productService;

	@Autowired(required = false)
	private SupplierService supplierService;
	
	@RequestMapping("/")
	public ModelAndView hello() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/supplier")
	@ResponseBody
	public ModelAndView supplierMain() {
		return new ModelAndView("supplier");
	}
	
	@RequestMapping("/about")
	@ResponseBody
	public ModelAndView aboutUs() {
		return new ModelAndView("about");
	}
	
	@RequestMapping("/product")
	@ResponseBody
	public ModelAndView productMain() {
		return new ModelAndView("product");
	}
	
	@RequestMapping("/log")
	@ResponseBody
	public ModelAndView logMain() {
		return new ModelAndView("log");
	}	
	
	@RequestMapping("/contact")
	@ResponseBody
	public ModelAndView contact() {
		return new ModelAndView("contact");
	}	
	
	
	@RequestMapping("/search")
	@ResponseBody
	public ModelAndView search(@RequestParam("query") String query) {
		ModelAndView mav = new ModelAndView("search");
		mav.addObject("products", productService.find(query));
		mav.addObject("suppliers", supplierService.find(query));
		return mav;
	}	
}
