package com.hackerrank.sample.service;

import java.util.List;

import com.hackerrank.sample.model.Sku;

public interface InventoryService {

	void deleteAllItems();

	void deleteItemById(Long id);

	Sku addItem(Sku model);
	
	Sku updateItem(Sku model,Long id);

	Sku getItemById(Long id);

	List<Sku> getAllItems();
}
