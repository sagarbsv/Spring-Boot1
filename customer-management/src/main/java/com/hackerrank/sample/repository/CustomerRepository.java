package com.hackerrank.sample.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.sample.model.Customer;
@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	 @Transactional
	    Long deleteById(Long id);
}
