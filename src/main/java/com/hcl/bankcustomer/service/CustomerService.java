package com.hcl.bankcustomer.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hcl.bankcustomer.exception.CustomerDataNotFoundException;
import com.hcl.bankcustomer.pojo.Customer;

public interface CustomerService {

	public List<Customer> addingCustomer(Customer customer)
			throws JsonParseException, JsonMappingException, IOException;

	public List<Customer> updatingCustomer(int id, String name,String role,int phoneNumber, String address)
			throws JsonParseException, JsonMappingException, IOException;

	public String deleteCustomer(int id) throws JsonParseException, JsonMappingException, IOException;

	public Customer searchCustomer(int id)
			throws CustomerDataNotFoundException, JsonParseException, JsonMappingException, IOException;

	public List<Customer> customers() throws JsonParseException, JsonMappingException, IOException;

}
