package com.hcl.bankcustomer.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hcl.bankcustomer.exception.CustomerDataNotFoundException;
import com.hcl.bankcustomer.pojo.Customer;
import com.hcl.bankcustomer.service.CustomerService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/bank")
@Api(value = "CustomerController", produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/addcustomer")
	public List<Customer> addingCustomer(@RequestBody Customer customer) throws JsonParseException, JsonMappingException, IOException {
		List<Customer> li = customerService.addingCustomer(customer);
		return li;

	}
	
	@PutMapping("/{id}/{phone}")
	public List<Customer> updatingCustomer(@RequestParam(required = false, name ="id") int id, @RequestParam(required = false, name ="name") String name,@RequestParam(required = false, name ="role") String role,@RequestParam(required = false, name ="phoneNumber") int phoneNumber,@RequestParam(required = false, name ="address") String address) throws JsonParseException, JsonMappingException, IOException{
		List<Customer> li=customerService.updatingCustomer(id, name, role, phoneNumber, address);
		if(li!=null && !li.isEmpty()) {
		return li;
		}
		else {
			 throw new CustomerDataNotFoundException("customer data does not exist with id "+id);
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException{
		String li= customerService.deleteCustomer(id);
		if(li!=null) {
		return li;
		}
		else {
			 throw new CustomerDataNotFoundException("customer data does not exist with id "+id);
		}
		
	}
	
	@GetMapping("/{id}")
	public Customer findCustomer(@PathVariable int id)throws CustomerDataNotFoundException, JsonParseException, JsonMappingException, IOException{
		Customer li= customerService.searchCustomer(id);
		if(li!=null) {
		return li;
		}
		else {
		 throw new CustomerDataNotFoundException("customer data does not exist with id "+id);
	}
	}
	
	@GetMapping("/")
	public List<Customer> customers() throws JsonParseException, JsonMappingException, IOException{
		return customerService.customers();
	}

}
