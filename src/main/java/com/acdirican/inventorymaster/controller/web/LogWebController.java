package com.acdirican.inventorymaster.controller.web;

import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.acdirican.inventorymaster.model.Log;
import com.acdirican.inventorymaster.service.base.LoggerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
 * Log web controller, handing web requests and directing to required views
 * (JSP).
 */

@Controller
@RequestMapping("/log")
public class LogWebController {

	@Autowired
	private LoggerService loggerService;

	@RequestMapping("/list")
	@ResponseBody
	public ModelAndView getAllLog(@RequestParam(name="product", required=false) Integer product) {
		ModelAndView mav = new ModelAndView();
		if (product ==null) {
			mav.addObject("logs", loggerService.list());
		}
		else {
			mav.addObject("logs", loggerService.listByProduct(product));
		}
		
		mav.setViewName("log_list");
		return mav;
	}
	

	@RequestMapping("/get/{id}")
	@ResponseBody
	public ModelAndView getLogs(@PathVariable(name="id", required=false) Integer product) {
		ModelAndView mav = new ModelAndView();
		if (product ==null) {
			mav.addObject("logs", loggerService.list());
		}
		else {
			mav.addObject("logs", loggerService.listByProduct(product));
		}
		
		mav.setViewName("log_ajax");
		return mav;
	}

	
	/**
	 * Returns a specific log with the given ID
	 */
	@RequestMapping("/{id}")
	@ResponseBody
	public ModelAndView getLog(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		Optional<Log> prev = loggerService.getWidthID(id);
		if (prev.isEmpty()) {
			mav.addObject("log", null);
		} else {
			mav.addObject("log", prev.get());
		}
		mav.setViewName("log_update");
		return mav;
	}

	/**
	 * Remove the log with the given ID
	 */
	@GetMapping("remove/{id}")
	public ModelAndView removeLog(@PathVariable("id") Integer id) {
		ModelAndView mav = new ModelAndView();
		if (loggerService.delete(id)) {
			mav.setViewName("redirect:/log/list");
		} else {
			mav.setViewName("redirect:/log/" + id);
		}

		return mav;
	}

	/**
	 * Remove the given log by redirecting "remove/{id}"
	 */
	@PostMapping("/remove")
	public String removeLog(@ModelAttribute Log log) {
		return "redirect:remove/" + log.getID();
	}

}