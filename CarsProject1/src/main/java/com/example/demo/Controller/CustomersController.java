package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.Entity.Customers;
import com.example.demo.Service.CustomersService;
import jakarta.servlet.http.HttpSession;

@RestController
public class CustomersController {
	
	@Autowired
	CustomersService customerService;
	
	
	@GetMapping(value = "/getCustomerId/{emailId}/{password}")
	public ResponseEntity<Object> getCustomerByCredentials(@PathVariable("emailId") String emailId, @PathVariable("password") String password) {
	    Customers customer = customerService.getCustomerByCredentials(emailId, password);
	    if (customer != null) {
	        return new ResponseEntity<>(customer.getCustomerId(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping(value = "/customerLogin")
	public ResponseEntity<Object> customerLogin(@RequestBody Customers customer)
	{
		boolean flag = customerService.findCustomer(customer);
		
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}

	@PostMapping(value = "/addCustomer")
	public ResponseEntity<Object> addCustomer(@RequestBody Customers customers){
		customerService.addCustomer(customers);
		return new ResponseEntity<Object>("Customer Added SuccessFully" ,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getallCustomers")
	public ResponseEntity<Object> getallCustomers(){
		ResponseEntity<Object> entity = new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping(value= "/getCustomer/{customerId}")     
	public ResponseEntity<Object> getCustomerByCustomerId(@PathVariable("customerId") Long customerId )
	{
		Customers customer;
		
		if(customerService.isCustomerExist(customerId)) 
		{
			customer = customerService.getCustomerByCustomerId(customerId);
		}
		else {
			customer = null;
		}
		ResponseEntity<Object> entity = new ResponseEntity<Object>(customer , HttpStatus.OK);
		return entity;
	}
	
	
	@DeleteMapping(value ="/deleteCustomer/{customerId}")
	public ResponseEntity<Object> deleteCuustomer(@PathVariable("customerId") Long customerId)
	{
		boolean flag;
		if(customerService.isCustomerExist(customerId))
		{
			flag = customerService.deleteCustomer(customerId);
		}
		else {
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}
	
	
	@PutMapping( value ="/updateCustomer/{customerId}")
	public ResponseEntity<Object> updateCustomer(@PathVariable("customerId") Long customerId , @RequestBody Customers customer)
	{
		boolean flag;
		if(customerService.isCustomerExist(customerId))
		{
			flag = customerService.updateCustomer(customer);
		}
		else 
		{
			flag = false;
		}
		return new ResponseEntity<Object>(flag, HttpStatus.OK);
	}	
	
////////////From Here Web-Service Starts..Above one's are Api's, that i have written for testing................
	
	@GetMapping("/CustomerHome")
	public ModelAndView adminHome(Model model, HttpSession session) {
		
		model.addAttribute("customerEmail", session.getAttribute("customerEmail"));
		System.out.println("curotmer id =" + model.getAttribute("customerEmail"));
		ModelAndView view = new ModelAndView("CustomerHome");
		return view;
	}
	
	@GetMapping("/CustomerLogins")
	public ModelAndView adminlogin(Model model) {
	    Customers customer = new Customers();
	    model.addAttribute("customer", customer);
	    ModelAndView view = new ModelAndView("CustomerLogins");
	    return view;
	}


	@PostMapping("/CustomerloginSuccess")
	public ModelAndView login(@ModelAttribute("customer") Customers customer, Model model, HttpSession session) {
		boolean isAuthenticated = customerService.findCustomer(customer);

		if (isAuthenticated) {
			session.setAttribute("customerEmail", customer.getEmailId());
			model.addAttribute("customerEmail", session.getAttribute("customerEmail"));
			ModelAndView view = new ModelAndView("redirect:/CustomerHome");
			return view;
			}
		else {
			ModelAndView view = new ModelAndView("redirect:/CustomerLogins");
			return view;
			}
		}
	
	
	//addingCustomer and addedCustomer Means == Signup
	@GetMapping("/addingCustomer")
	public ModelAndView addingCustomer(Model model)
	{
		Customers customer = new Customers();
		model.addAttribute("customer" , customer);
		ModelAndView view = new ModelAndView("addcustomer");
		return view;
	}
	
	@PostMapping("/addedCustomer")
	public ModelAndView addedCustomer(@ModelAttribute("customer") Customers customer, Model model, HttpSession session) {
	    if (customerService.addCustomer(customer)) {
	    	session.setAttribute("customerEmail", customer.getEmailId());
			model.addAttribute("customerEmail", session.getAttribute("customerEmail"));
			System.out.println("curotmer-Email =" + model.getAttribute("customerEmail"));
	        ModelAndView view = new ModelAndView("redirect:/addingCustomer");
	        return view;
	    } 
	    else {
	        ModelAndView errorView = new ModelAndView("errorPage");
	        return errorView;
	    }
	}

	@GetMapping("/Logout")
	public ModelAndView Logout() {
		return new ModelAndView("Logout");
	}
	
	
	@GetMapping("/viewAllCustomers")
	public ModelAndView viewAllCustomers(Model model) {
	    List<Customers> customerList = customerService.getAllCustomers();
	    model.addAttribute("customerList", customerList);
	    return new ModelAndView("viewAllCustomers");
	}

	@GetMapping("/editCustomer/{customerId}")
	public ModelAndView editCustomer(@PathVariable("customerId") Long customerId, Model model, HttpSession session) {
	    Customers customer = customerService.getCustomerByCustomerId(customerId);
	    if (customer != null) {
	        model.addAttribute("customer", customer);
	        ModelAndView view = new ModelAndView("editCustomer");
	        return view;
	    } else {
	        ModelAndView errorView = new ModelAndView("errorPage");
	        return errorView;
	    }
	}

	@PostMapping("/UpdateCustomer")
	public ModelAndView UpdateCustomer(@ModelAttribute("customers") Customers customers, Model model) {
	    if (customerService.updateCustomer(customers)) {
	        ModelAndView view = new ModelAndView("redirect:/viewAllCustomers");
	        return view;
	    } else {
	        ModelAndView errorView = new ModelAndView("errorPage");
	        return errorView;
	    }
	}
	
	@GetMapping("/deleteCustomer/{customerId}")
	public ModelAndView deleteCustomer(@PathVariable("customerId") Long customerId) {
	    if (customerService.deleteCustomer(customerId)) {
	        ModelAndView view = new ModelAndView("redirect:/viewAllCustomers");
	        return view;
	    } else {
	        ModelAndView errorView = new ModelAndView("errorPage");
	        return errorView;
	    }
	}
}