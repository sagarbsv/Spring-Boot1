package com.hackerrank.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Vendor;
import com.hackerrank.sample.repository.VendorRepository;
@Service("VendorService")
public class VendorServiceImpl implements VendorService{
	
	@Autowired
	private VendorRepository vendorRepository;

	 @Override
	    public void deleteAllVendors() {
	        vendorRepository.deleteAllInBatch();
	    }

	    @Override
	    public void deleteVendorById(Long id) {
	                 Vendor existingVendor = vendorRepository.findOne(id);
	                 if (existingVendor == null) {
	             throw new NoSuchResourceFoundException("Vendor with the id not exists.");
	         }
	        vendorRepository.delete(id);
	    }
  
	    @Override
	    public Vendor createVendor(Vendor Vendor)  {
	        Vendor existingVendor = vendorRepository.findOne(Vendor.getVendorId());

	        if (existingVendor != null) {
	            throw new BadResourceRequestException("Vendor with same id exists.");
	        }

	        Vendor savedVendor= vendorRepository.save(Vendor);
	        return savedVendor;
	    }

	    @Override
	    public Vendor updateVendor(Vendor Vendor,Long id)  {
	        Vendor existingVendor = vendorRepository.findOne(id);
	     
	        if (existingVendor == null) {
	            throw new NoSuchResourceFoundException("Vendor with the id not exists.");
	        }
	       
	        Vendor savedVendor= vendorRepository.save(Vendor);
	        return savedVendor;
	    }
	    
	    @Override
	    public Vendor getVendorById(Long id) { 
	        Vendor Vendor = vendorRepository.findOne(id);

	        if (Vendor == null) {
	            throw new NoSuchResourceFoundException("No Vendor with given id found.");
	        }

	        return Vendor;
	    }

	    @Override
	    public List<Vendor> getAllVendors() {
	                List<Vendor>  VendorList =vendorRepository.findAll();
	                 if (VendorList.isEmpty()) {
	             throw new NoSuchResourceFoundException("No Vendor with given id found.");
	         }
	                 
	                 return VendorList;

	    }
	

}
