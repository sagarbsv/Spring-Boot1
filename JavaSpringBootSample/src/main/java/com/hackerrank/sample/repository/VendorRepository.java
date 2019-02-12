package com.hackerrank.sample.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.sample.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{
	@Transactional
    Long deleteById(Long id);

}
