package com.hackerrank.sample.service;

import com.hackerrank.sample.model.Customer;

import java.util.List;

public interface CustomerService {

	void deleteAllCustomers();

	void deleteCustomerById(Long id);

	Customer createCustomer(Customer model);
	
	Customer updateCustomer(Customer model,Long id);

	Customer getCustomerById(Long id);

	List<Customer> getAllCustomers();

}
