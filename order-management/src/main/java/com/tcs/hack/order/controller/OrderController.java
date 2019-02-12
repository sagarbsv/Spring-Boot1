package com.tcs.hack.order.controller;

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

import com.tcs.hack.order.exceptions.BadResourceRequestException;
import com.tcs.hack.order.exceptions.NoSuchResourceFoundException;
import com.tcs.hack.order.model.Order;
import com.tcs.hack.order.service.OrderService;
@RestController
public class OrderController {
	@Autowired
	OrderService orderservice;
	
	@RequestMapping(value = "/Orders", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createNewOrder(@RequestBody @Valid Order Order) throws Exception {

		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(orderservice.createOrder(Order), HttpStatus.CREATED);
		} catch (BadResourceRequestException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;

	}

	@RequestMapping(value = "/Orders/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)

	public ResponseEntity<Object> createExistingOrder(@RequestBody @Valid Order Order, @PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(orderservice.updateOrder(Order, id), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@RequestMapping(value = "/Orders", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllOrders() {
		orderservice.deleteAllOrders();  
	}

	@RequestMapping(value = "/Orders/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> deleteOrderById(@PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			orderservice.deleteOrderById(id);
			response = new ResponseEntity<Object>(HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@RequestMapping(value = "/Orders", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAllOrders() {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(orderservice.getAllOrders(), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}

	@RequestMapping(value = "/Orders/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getOrderById(@PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(orderservice.getOrderById(id), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}
}
