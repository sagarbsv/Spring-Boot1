package com.hackerrank.sample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Customer;
import com.hackerrank.sample.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customers", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createNewCustomer(@RequestBody @Valid Customer customer) throws Exception {

		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(customerService.createCustomer(customer), HttpStatus.CREATED);
		} catch (BadResourceRequestException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;

	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)

	public ResponseEntity<Object> updateCustomer(@RequestBody @Valid Customer Customer, @PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(customerService.updateCustomer(Customer, id), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllCustomers() {
		customerService.deleteAllCustomers();   
	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> deleteCustomerById(@PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			customerService.deleteCustomerById(id);
			response = new ResponseEntity<Object>(HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAllCustomers() {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(customerService.getAllCustomers(), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(customerService.getCustomerById(id), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}
	
	
	
}
