package com.hackerrank.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Sku;
import com.hackerrank.sample.repository.InventoryRepository;

@Service("InventoryService")
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	InventoryRepository inventoryRepository;

	@Override
    public void deleteAllItems() {
        inventoryRepository.deleteAllInBatch();
    }

    @Override
    public void deleteItemById(Long id) {
                 Sku existingItem = inventoryRepository.findOne(id);
                 if (existingItem == null) {
             throw new NoSuchResourceFoundException("Item with the id not exists.");
         }
        inventoryRepository.delete(id);
    }

    @Override
    public Sku addItem(Sku Item)  {
        Sku existingItem = inventoryRepository.findOne(Item.getId());  

        if (existingItem != null) {
            throw new BadResourceRequestException("Item with same id exists.");
        }

        Sku savedItem= inventoryRepository.save(Item);
        return savedItem;
    }

    @Override
    public Sku updateItem(Sku Item,Long id)  {
        Sku existingItem = inventoryRepository.findOne(id);
     
        if (existingItem == null) {
            throw new NoSuchResourceFoundException("Item with the id not exists.");
        }
       
        Sku savedItem= inventoryRepository.save(Item);
        return savedItem;
    }
    
    @Override
    public Sku getItemById(Long id) { 
        Sku Item = inventoryRepository.findOne(id);

        if (Item == null) {
            throw new NoSuchResourceFoundException("No Item with given id found.");
        }

        return Item;
    }

    @Override
    public List<Sku> getAllItems() {
                List<Sku>  ItemList =inventoryRepository.findAll();
                 if (ItemList.isEmpty()) {
             throw new NoSuchResourceFoundException("No Item with given id found.");
         }
                 
                 return ItemList;

    }
}
