package com.hackerrank.sample.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.sample.model.Sku;

public interface InventoryRepository extends JpaRepository<Sku, Long>{
	@Transactional
    Long deleteById(Long id);
}
