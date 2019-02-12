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
import com.hackerrank.sample.model.Sku;
import com.hackerrank.sample.service.InventoryService;

@RestController
public class InventoryController {
	@Autowired
	InventoryService inventoryService;
	
	@RequestMapping(value = "/items", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createNewItem(@RequestBody @Valid Sku item) throws Exception {

		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(inventoryService.addItem(item), HttpStatus.CREATED);
		} catch (BadResourceRequestException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;

	}

	@RequestMapping(value = "/items/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)

	public ResponseEntity<Object> createExistingItem(@RequestBody @Valid Sku item, @PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(inventoryService.updateItem(item, id), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@RequestMapping(value = "/items", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllitems() {
		inventoryService.deleteAllItems();   
	}

	@RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> deleteItemById(@PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			inventoryService.deleteItemById(id);
			response = new ResponseEntity<Object>(HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAllitems() {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(inventoryService.getAllItems(), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}

	@RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getItemById(@PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(inventoryService.getItemById(id), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}
}
