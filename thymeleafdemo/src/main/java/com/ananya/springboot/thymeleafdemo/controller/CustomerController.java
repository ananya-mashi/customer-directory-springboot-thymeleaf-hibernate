package com.ananya.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ananya.springboot.thymeleafdemo.entity.Customer;
import com.ananya.springboot.thymeleafdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService cService;
	@Autowired
	public CustomerController(CustomerService theService) {
		cService = theService;
	}
	

	@GetMapping("/list")
	public String listCustomer(Model theModel) {
		
		// get customers from db
		List<Customer> theCustomer = cService.findAll();
		
		// add to the spring model
		theModel.addAttribute("customer", theCustomer);
		
		return "list-customer";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
Customer thecustomer = new Customer();
		
		theModel.addAttribute("customer", thecustomer);
		
		return "customer-form";
	}
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int id,
									Model theModel) {
		
		Customer theCustomer = cService.findById(id);
		
	
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";			
	}
	
	@PostMapping("/save")
	public String savecustomer(@ModelAttribute("customer") Customer thecustomer) {
		
		// save the customer
		cService.save(thecustomer);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/list";
	}

	
	@PostMapping("/showFormForDelete")
	public String delete(@RequestParam("id") int id) {
		
		cService.deleteById(id);
		
		// redirect to /employees/list
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("firstName") String theFirstName,
						 @RequestParam("lastName") String theLastName,
						 Model theModel) {
		
		// check names, if both are empty then just give list of all employees

		if (theFirstName.trim().isEmpty() && theLastName.trim().isEmpty()) {
			return "redirect:/customer/list";
		}
		else {
			// else, search by first name and last name
			List<Customer> theCustomer =
							cService.searchBy(theFirstName, theLastName);
			
			// add to the spring model
			theModel.addAttribute("customer", theCustomer);
			
			// send to list-employees
			return "list-customer";
		}
		
	}
}









