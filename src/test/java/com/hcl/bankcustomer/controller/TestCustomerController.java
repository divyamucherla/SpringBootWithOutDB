package com.hcl.bankcustomer.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bankcustomer.exception.CustomerDataNotFoundException;
import com.hcl.bankcustomer.pojo.Customer;
import com.hcl.bankcustomer.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestContext.class, CustomerController.class })
@WebAppConfiguration
public class TestCustomerController {

	@InjectMocks
	CustomerController customerController;

	private MockMvc mockMvc;

	@Mock
	CustomerService customerService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testaddingCustomer() throws JsonProcessingException, Exception {
		List<Customer> li = new ArrayList<>();
		Customer customer = new Customer();
		customer.setAddress("address");
		customer.setId(12);
		customer.setName("name");
		customer.setPhoneNumber(1234);
		customer.setRole("role");
		li.add(customer);
		Mockito.when(customerService.addingCustomer(Mockito.anyObject())).thenReturn(li);
		this.mockMvc
				.perform(post("/addcustomer").contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(li)))
				.andReturn();
		List<Customer> li1 = customerController.addingCustomer(customer);
		assertEquals(li, li1);

	}

	@Test
	public void testupdatingCustomer() throws JsonProcessingException, Exception {

		int id = 12;
		int phone = 1234;
		String name="name";
		String role="role";
		String address="address";
		List<Customer> li = new ArrayList<>();
		Customer customer = new Customer();
		customer.setAddress("address");
		customer.setId(12);
		customer.setName("name");
		customer.setPhoneNumber(1234);
		customer.setRole("role");
		li.add(customer);
		Mockito.when(customerService.updatingCustomer(Mockito.anyInt(),Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyString())).thenReturn(li);
		this.mockMvc.perform(put("/{id}/{phone}", 12,"name","role",1234,"address").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(id).concat(asJsonString(name)).concat(asJsonString(role)).concat(asJsonString(phone)).concat(asJsonString(address)))).andReturn();
		List<Customer> li1 = customerController.updatingCustomer(id, name, role, phone, address);
		assertEquals(li, li1);
	}

	@Test
	public void testdeleteCustomer() throws JsonProcessingException, Exception {
		int id = 1234;
		String str = "value";
		Mockito.when(customerService.deleteCustomer(Mockito.anyInt())).thenReturn("value");
		this.mockMvc
				.perform(delete("/{id}", 1234).contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(id)))
				.andReturn();
		String str1 = customerController.deleteCustomer(id);
		assertEquals(str, str1);
	}

	@Test
	public void testfindCustomer() throws JsonProcessingException, Exception {
		int id = 1234;
		Customer customer = new Customer();
		customer.setAddress("address");
		customer.setId(12);
		customer.setName("name");
		customer.setPhoneNumber(1234);
		customer.setRole("role");
		Mockito.when(customerService.searchCustomer(Mockito.anyInt())).thenReturn(customer);
		this.mockMvc.perform(get("/{id}", 1234).contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(id)))
				.andReturn();
		Customer customer1 = customerController.findCustomer(id);
		assertEquals(customer, customer1);
	}

	@Test(expected = NestedServletException.class)
	public void testfindCustomer_1() throws JsonProcessingException, Exception {
		int id = 1234;
		Customer customer = new Customer();
		customer.setAddress("address");
		customer.setId(12);
		customer.setName("name");
		customer.setPhoneNumber(1234);
		customer.setRole("role");
		Mockito.when(customerService.searchCustomer(Mockito.anyInt())).thenReturn(null);
		this.mockMvc.perform(get("/{id}", 1234).contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(id)))
				.andReturn();
		// .equals( new CustomerDataNotFoundException("customer data does not exist with
		// id "+id));
		customerController.findCustomer(id);

	}

	public static String asJsonString(final Object obj) throws JsonProcessingException {

		return new ObjectMapper().writeValueAsString(obj);

	}

}
