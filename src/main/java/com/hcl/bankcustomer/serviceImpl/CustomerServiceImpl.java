package com.hcl.bankcustomer.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hcl.bankcustomer.exception.CustomerDataNotFoundException;
import com.hcl.bankcustomer.pojo.Customer;
import com.hcl.bankcustomer.pojo.CustomerDetails;
import com.hcl.bankcustomer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	List<Customer> li;

	@Override
	public List<Customer> addingCustomer(Customer customer) {
		li = CustomerDetails.createCustomer();
		li.add(customer);
		if (li != null && !li.isEmpty()) {
			return li;
		} else {
			return null;
		}
	}

	// private List<Customer> createCustomer() {}

	@Override
	public List<Customer> updatingCustomer(int id, int phone) {
		List<Customer> temp = CustomerDetails.createCustomer();
		temp.forEach(customer -> {
			if (customer.getId() == (id)) {
				customer.setPhoneNumber(phone);
			}
		});
		return temp;
	}

	@Override
	public String deleteCustomer(int id) {
		List<Customer> temp = CustomerDetails.createCustomer();
		Map<Integer, String> map = new HashMap<>();
		for (Customer customer : temp) {
			map.put(customer.getId(), customer.getName());

		}
		map.remove(id);
		return "record deleted";
	}

	public Customer searchCustomer(int id) throws CustomerDataNotFoundException {

		Customer customerEntity = new Customer();
		List<Customer> temp = CustomerDetails.createCustomer();
		for (Customer customer : temp) {
			if (customer.getId() == id) {
				customerEntity = customer;

			}

		}
		return customerEntity;
	}
}
