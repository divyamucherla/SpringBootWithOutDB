package com.hcl.bankcustomer.service;

import java.util.List;

import com.hcl.bankcustomer.exception.CustomerDataNotFoundException;
import com.hcl.bankcustomer.pojo.Customer;

public interface CustomerService {

	public List<Customer> addingCustomer(Customer customer);

	public List<Customer> updatingCustomer(int id,int phone);
	
	public String deleteCustomer(int id);
	
	public Customer searchCustomer(int id) throws CustomerDataNotFoundException;

}
