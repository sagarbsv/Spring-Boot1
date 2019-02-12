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
import com.hackerrank.sample.model.Vendor;
import com.hackerrank.sample.service.VendorService;

@RestController
public class VendorController {
	@Autowired
	VendorService vendorservice;
	
	@RequestMapping(value = "/vendors", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createNewVendor(@RequestBody @Valid Vendor Vendor) throws Exception {

		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(vendorservice.createVendor(Vendor), HttpStatus.CREATED);
		} catch (BadResourceRequestException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;

	}

	@RequestMapping(value = "/vendors/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)

	public ResponseEntity<Object> createExistingVendor(@RequestBody @Valid Vendor Vendor, @PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(vendorservice.updateVendor(Vendor, id), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@RequestMapping(value = "/vendors", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllvendors() {
		vendorservice.deleteAllVendors();  
	}

	@RequestMapping(value = "/vendors/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> deleteVendorById(@PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			vendorservice.deleteVendorById(id);
			response = new ResponseEntity<Object>(HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAllvendors() {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(vendorservice.getAllVendors(), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}

	@RequestMapping(value = "/vendors/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getVendorById(@PathVariable Long id) {
		ResponseEntity<Object> response = null;
		try {
			response = new ResponseEntity<Object>(vendorservice.getVendorById(id), HttpStatus.OK);
		} catch (NoSuchResourceFoundException bex) {
			response = new ResponseEntity<Object>(bex.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}
}
