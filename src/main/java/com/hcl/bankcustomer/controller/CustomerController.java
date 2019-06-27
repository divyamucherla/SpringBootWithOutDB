package com.hcl.bankcustomer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankcustomer.exception.CustomerDataNotFoundException;
import com.hcl.bankcustomer.pojo.Customer;
import com.hcl.bankcustomer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/addcustomer")
	public List<Customer> addingCustomer(@RequestBody Customer customer) {
		List<Customer> li = customerService.addingCustomer(customer);
		return li;

	}
	
	@PutMapping("/{id}/{phone}")
	public List<Customer> updatingCustomer(@PathVariable int id, @PathVariable int phone){
		List<Customer> li=customerService.updatingCustomer(id, phone);
		return li;
	}
	
	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable int id){
		String li= customerService.deleteCustomer(id);
		return li;
		
	}
	
	@GetMapping("/{id}")
	public Customer findCustomer(@PathVariable int id)throws CustomerDataNotFoundException{
		Customer li= customerService.searchCustomer(id);
		if(li!=null) {
		return li;
		}
		else {
		 throw new CustomerDataNotFoundException("customer data does not exist with id "+id);
	}
	}

}
