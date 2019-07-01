package com.hcl.bankcustomer.serviceImpl.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hcl.bankcustomer.pojo.Customer;
import com.hcl.bankcustomer.serviceImpl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
	
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	
	
	@Test
	public void testaddingCustomer() throws JsonParseException, JsonMappingException, IOException
	{
	List<Customer> li = new ArrayList<>();
		Customer customer=new Customer();
		customer.setAddress("address");
		customer.setId(12);
		customer.setName("name");
		customer.setPhoneNumber(1234);
		customer.setRole("role");
		li.add(customer);
		List<Customer> li1=customerServiceImpl.addingCustomer(customer);
		assertEquals(3, li1.size());
		
	}
	
	@Test
	public void testupdatingCustomer() throws JsonParseException, JsonMappingException, IOException {
		int id=12;
		int phone=1234;
		String name="name";
		String role="role";
		String address="address";
		List<Customer> li = new ArrayList<>();
		Customer customer=new Customer();
		customer.setId(12);
		customer.setPhoneNumber(1234);
		li.add(customer);
		List<Customer> li1=customerServiceImpl.updatingCustomer(id, name, role, phone, address);
		assertEquals(9, li1.size());
	}
	
	@Test
	public void testdeleteCustomer() throws JsonParseException, JsonMappingException, IOException{
		int id=1234;
		Customer customer=new Customer();
		customer.setId(12);
		customer.setName("name");
		String str=customerServiceImpl.deleteCustomer(id);
		assertEquals("record deleted", str);
	}
	
	@Test
	public void testsearchCustomer() throws JsonParseException, JsonMappingException, IOException {
		int id=10;
		Customer cust1 = new Customer();
		cust1.setId(10);
		cust1.setAddress("Hyd");
		cust1.setName("Shiva garu");
		cust1.setPhoneNumber(923243);
		cust1.setRole("SSE");
		customerServiceImpl.searchCustomer(id);
		assertEquals("Hyd",cust1.getAddress() );
	}
	
	

}
