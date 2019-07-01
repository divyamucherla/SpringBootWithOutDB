package com.hcl.bankcustomer.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bankcustomer.pojo.Customer;
import com.hcl.bankcustomer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	static List<Customer> jsonList = new ArrayList<Customer>();

	public static List<Customer> json() throws JsonParseException, JsonMappingException, IOException {
		/*
		 * JSONParser jsonParser = new JSONParser(); FileReader fl = new
		 * FileReader("src\\main\\resources\\static\\Details.json"); Object obj =
		 * jsonParser.parse(fl); JSONArray List = (JSONArray) obj;
		 */
		ObjectMapper obj1 = new ObjectMapper();
		TypeReference<List<Customer>> customerTypeRe = new TypeReference<List<Customer>>() {
		};
		InputStream input = TypeReference.class.getResourceAsStream("src\\main\\resources\\static\\Details.json");
		jsonList = obj1.readValue(input, customerTypeRe);

		return jsonList;
	}

	@Override
	public List<Customer> addingCustomer(Customer customer) throws JsonParseException, JsonMappingException, IOException {
		jsonList.add(customer);
		return jsonList;
	}

	@Override
	public List<Customer> updatingCustomer(int id, String name,String role,int phoneNumber, String address) throws JsonParseException, JsonMappingException, IOException{
//		Customer customer = new Customer();
//		List<Customer> li = new ArrayList<Customer>();
//		for (int i = 0; i < jsonList.size(); i++) {
//			for (Customer cust : li) {
//				if (cust.getId() == id) {
//					customer = cust;
//					li.add(customer);
//				}
//
//			}
//		}
//		return li;
		List<Customer> li = new ArrayList<Customer>();
		Customer customer = null;
		for (int i = 0; i < jsonList.size(); i++) {
			customer = jsonList.get(i);
			if (customer.getId() == id) {
				customer.setPhoneNumber(phoneNumber);
				jsonList.set(i, customer);
			}

		}
		return jsonList;

	}

	@Override
	public String deleteCustomer(int id) throws JsonParseException, JsonMappingException, IOException {
		Map<Integer, String> map = new HashMap<>();
		for (Customer customer : jsonList) {
			map.put(customer.getId(), customer.getName());

		}
		map.remove(id);
		return "record deleted";
	}

	public Customer searchCustomer(int id) throws JsonParseException, JsonMappingException, IOException {
		Customer customerEntity = new Customer();

		for (Customer customer : jsonList) {
			if (customer.getId() == id) {
				customerEntity = customer;

			}

		}
		return customerEntity;
	}

	@Override
	public List<Customer> customers() throws JsonParseException, JsonMappingException, IOException{
		return jsonList;
	}
}
