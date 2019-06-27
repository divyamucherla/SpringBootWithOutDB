package com.hcl.bankcustomer.pojo;

import java.util.ArrayList;
import java.util.List;

public class CustomerDetails {
 static	List<Customer> li = new ArrayList<Customer>();
	
	public static List<Customer> createCustomer() {

		Customer cust = new Customer();
		cust.setId(100);
		cust.setAddress("Hyderabad");
		cust.setName("Suresh garu");
		cust.setPhoneNumber(9876543);
		cust.setRole("SSE");
		Customer cust1 = new Customer();
		cust1.setId(10);
		cust1.setAddress("Hyd");
		cust1.setName("Shiva garu");
		cust1.setPhoneNumber(923243);
		cust1.setRole("SSE");
		li.add(cust1);
		li.add(cust);
		return li;
	}

}
