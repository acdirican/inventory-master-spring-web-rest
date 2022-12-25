package com.acdirican.inventorymaster.controller.web;

import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.acdirican.inventorymaster.model.Product;
import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.service.base.ProductService;
import com.acdirican.inventorymaster.service.base.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Product web controller, handing web requests and directing to required views (JSP). 
 */

@Controller
@RequestMapping("/product")
public class ProductWebController {

	@Autowired
	private ProductService productService;

	@Autowired(required = false)
	private SupplierService supplierService;
	
	@RequestMapping("/list")
	@ResponseBody
	public ModelAndView getAllProduct() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("products", productService.list());
		mav.setViewName("product_list");
		return mav;
	}

	/**
	 *Returns a specific product with the given ID
	 */
	@RequestMapping("/{id}")
	@ResponseBody
	public ModelAndView getProduct(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		Optional<Product> prev = productService.getWidthID(id);
		if (prev.isEmpty()) {
			mav.addObject("product", null);
			mav.addObject("logs", 	null);
		} else {
			mav.addObject("product", prev.get());
			mav.addObject("logs", 	prev.get().getLogs());
			mav.addObject("suppliers", 	supplierService.list());
		}
		mav.setViewName("product_update");
		return mav; 
	}

	/**
	 *Returns the products of a product with the given ID
	 */
	@RequestMapping("/{id}/products")
	public ModelAndView getProductProducts(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		var productOptional = productService.getWidthID(id);
		if (productOptional.isPresent()) {
			mav.addObject("product", productOptional.get());
			mav.addObject("logs", productOptional.get().getLogs());
		} else {
			mav.addObject("product", null);
			mav.addObject("logs", null);
		}
		mav.setViewName("product_products");
		return mav;
	}

	@GetMapping("/add")
	public ModelAndView addProduct() {
		ModelAndView mav = new ModelAndView("product_add");
		mav.addObject("suppliers", supplierService.list());
		
		return mav; 
	}
	
	@PostMapping("/add")
	public ModelAndView addProduct(@ModelAttribute Product product) {
		
		String idStr = product.getSupplier().getName(); //The ID in the from is put into the name attribute
		int id = Integer.parseInt(idStr);
		Supplier supplier = supplierService.getWidthID(id).get();
		Product newProduct = new Product(product.getName(), product.getQuantity(), supplier);
		
		Optional<Product> added = productService.add(newProduct);
		if (added.isPresent()) {
			return new ModelAndView("redirect:" + added.get().getID()); 
		}
		else {
			return new ModelAndView("add"); 
		}
	}
	
	/**
	 * Update product by converting POST data to an product instance
	 */
	@PostMapping(path = "/update")
	@ResponseBody
	public ModelAndView updateProduct(@ModelAttribute Product product) {
			ModelAndView mav = new ModelAndView();
			var productOptional = productService.getWidthID(product.getID());
			if (productOptional.isPresent()) {
				Product toUpdate = productOptional.get();
				
				//The ID in the from is put into the name attribute
				String idStr = product.getSupplier().getName(); 
				int id = Integer.parseInt(idStr);
				Supplier supplier = supplierService.getWidthID(id).get();
				
				toUpdate.setName(product.getName());
				toUpdate.setSupplier(supplier);
				toUpdate.setQuantity(product.getQuantity());
				System.out.println("--->" + toUpdate);
				Product updated = productService.update(toUpdate);
				System.out.println("*****->" + updated);
			} else {
				mav.addObject("product", null);
			}
			mav.setViewName("redirect:"+product.getID());
			return mav;
	}
	
		
	/**
	 *Remove the product with the given ID
	 */
	@GetMapping("remove/{id}")
	public ModelAndView removeProduct(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		if (productService.delete(id)) {
			mav.setViewName("redirect:/product/list");
		}
		else {
			mav.setViewName("redirect:/product/" + id);
		}
		
		return mav;
	}
	
	/**
	 *Remove the given product by redirecting "remove/{id}"
	 */
	@PostMapping("/remove")
	public String removeProduct(@ModelAttribute Product product) {
		return "redirect:remove/" + product.getID();
	}
	
	
	@RequestMapping("/search")
	@ResponseBody
	public ModelAndView search(@RequestParam(name = "query", required = false) String query) {
		ModelAndView mav = new ModelAndView();
		if (query!=null) {
			mav.addObject("products", productService.find(query));
			mav.setViewName("product_list");
		}
		else {
			mav.setViewName("entity_search");
		}
		
		return mav;
	}	

}