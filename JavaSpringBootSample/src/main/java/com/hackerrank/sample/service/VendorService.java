package com.hackerrank.sample.service;

import java.util.List;

import com.hackerrank.sample.model.Vendor;;

public interface VendorService {

	void deleteAllVendors();

	void deleteVendorById(Long id);

	Vendor createVendor(Vendor model);
	
	Vendor updateVendor(Vendor model,Long id);

	Vendor getVendorById(Long id);

	List<Vendor> getAllVendors();

}
