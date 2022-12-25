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

import com.acdirican.inventorymaster.model.Supplier;
import com.acdirican.inventorymaster.service.base.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * Supplier web controller, handing web requests and directing to required views (JSP). 
 */

 
@Controller
@RequestMapping("/supplier")
public class SupplierWebController {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping("/list")
	@ResponseBody
	public ModelAndView getAllSupplier() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("suppliers", supplierService.list());
		mav.setViewName("supplier_list");
		return mav;
	}

	/**
	 *Returns a specific supplier with the given ID
	 */
	@RequestMapping("/{id}")
	@ResponseBody
	public ModelAndView getSupplier(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		Optional<Supplier> prev = supplierService.getWidthID(id);
		if (prev.isEmpty()) {
			mav.addObject("supplier", null);
		} else {
			mav.addObject("supplier", prev.get());
			mav.addObject("products", 	prev.get().getProducts());
		}
		mav.setViewName("supplier_update");
		return mav;
	}

	/**
	 *Returns the products of a supplier with the given ID
	 */
	@RequestMapping("/{id}/products")
	public ModelAndView getSupplierProducts(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		var supplierOptional = supplierService.getWidthID(id);
		if (supplierOptional.isPresent()) {
			mav.addObject("supplier", supplierOptional.get());
			mav.addObject("products", supplierOptional.get().getProducts());
		} else {
			mav.addObject("supplier", null);
			mav.addObject("products", null);
		}
		mav.setViewName("supplier_products");
		return mav;
	}

	@GetMapping("/add")
	public ModelAndView addSupplier() {
		return new ModelAndView("supplier_add"); 
	}
	
	@PostMapping(path="/add")
	public ModelAndView addSupplier(@ModelAttribute Supplier supplier) {

		Optional<Supplier> added = supplierService.add(supplier);
		if (added.isPresent()) {
			return new ModelAndView("redirect:" + added.get().getID()); 
		}
		else {
			return new ModelAndView("add"); 
		}
	}
	
	/**
	 * Update supplier by converting POST data to an supplier instance
	 */
	@PostMapping(path = "/update")
	@ResponseBody
	public ModelAndView updateSupplier(@ModelAttribute Supplier supplier) {

			ModelAndView mav = new ModelAndView();
			var supplierOptional = supplierService.getWidthID(supplier.getID());
			if (supplierOptional.isPresent()) {
				supplierOptional.get().setName(supplier.getName());
				Supplier updated = supplierService.update(supplierOptional.get());
				mav.addObject("supplier", 	updated);
				mav.addObject("products", 	updated.getProducts());
				
			} else {
				mav.addObject("supplier", null);
			}
			mav.setViewName("redirect:"+supplier.getID());
			return mav;
	}
	
	/**
	 * Update supplier by parsing post data
	 */
	@PostMapping(path = "/update_by_parsing_post_data")
	@ResponseBody
	public ModelAndView updateSupplier(@RequestBody String query) {
		var post = parseQuery(query);
		ModelAndView mav = new ModelAndView();
		var supplierOptional = supplierService.getWidthID(Integer.parseInt(post.get("ID")));
		if (supplierOptional.isPresent()) {
			supplierOptional.get().setName(post.get("name"));
			supplierService.update(supplierOptional.get());
			mav.addObject("supplier", supplierOptional.get());
		} else {
			mav.addObject("supplier", null);
		}
		mav.setViewName("redirect:"+supplierOptional.get().getID());
		return mav;
	}
	
	/**
	 * Update supplier by getting parameters from query string
	 */
	@GetMapping(value = "/update_by_get", params= {"ID", "name"})
	@ResponseBody
	public ModelAndView updateSupplier(@RequestParam("ID") Integer id, @RequestParam("name") String name) {
			ModelAndView mav = new ModelAndView();
			var supplierOptional = supplierService.getWidthID(id);
			if (supplierOptional.isPresent()) {
				supplierOptional.get().setName(name);
				supplierService.update(supplierOptional.get());
				mav.addObject("supplier", supplierOptional.get());
			} else {
				mav.addObject("supplier", null);
			}
			mav.setViewName("redirect:"+id);
			return mav;
	}
	
	/**
	 *Remove the supplier with the given ID
	 */
	@GetMapping("remove/{id}")
	public ModelAndView removeSupplier(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		if (supplierService.delete(id)) {
			mav.setViewName("redirect:/supplier/list");
		}
		else {
			mav.setViewName("redirect:/supplier/" + id);
		}
		
		return mav;
	}
	
	/**
	 *Remove the given supplier by redirecting "remove/{id}"
	 */
	@PostMapping("/remove")
	public String removeSupplier(@ModelAttribute Supplier supplier) {
		return "redirect:remove/" + supplier.getID();
	}

	
	public Map<String, String> parseQuery(String query){
		Pattern pat = Pattern.compile("([^&=]+)=([^&]*)");
	    Matcher matcher = pat.matcher(query);
	    Map<String,String> map = new HashMap<>();
	    while (matcher.find()) {
	        map.put(matcher.group(1), matcher.group(2));
	    }
	    return map;
	}

	

	@RequestMapping("/search")
	@ResponseBody
	public ModelAndView search(@RequestParam(name = "query", required = false) String query) {
		ModelAndView mav = new ModelAndView();
		if (query!=null) {
			mav.addObject("suppliers", supplierService.find(query));
			mav.setViewName("supplier_list");
		}
		else {
			mav.setViewName("entity_search");
		}
		
		return mav;
	}	
	
}