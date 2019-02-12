package com.tcs.hack.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.hack.order.exceptions.BadResourceRequestException;
import com.tcs.hack.order.exceptions.NoSuchResourceFoundException;
import com.tcs.hack.order.model.Order;
import com.tcs.hack.order.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;

	 public void deleteAllOrders() {
		 orderRepo.deleteAllInBatch();
	    }

	    
	    public void deleteOrderById(Long id) {
	                 Order existingOrder = orderRepo.findOne(id);
	                 if (existingOrder == null) {
	             throw new NoSuchResourceFoundException("Order with the id not exists.");
	         }
	                 orderRepo.delete(id);
	    }
  
	    
	    public Order createOrder(Order Order)  {
	        Order existingOrder = orderRepo.findOne(Order.getOrderId());

	        if (existingOrder != null) {
	            throw new BadResourceRequestException("Order with same id exists.");
	        }

	        Order savedOrder= orderRepo.save(Order);
	        return savedOrder;
	    }

	    
	    public Order updateOrder(Order Order,Long id)  {
	        Order existingOrder = orderRepo.findOne(id);
	     
	        if (existingOrder == null) {
	            throw new NoSuchResourceFoundException("Order with the id not exists.");
	        }
	       
	        Order savedOrder= orderRepo.save(Order);
	        return savedOrder;
	    }
	    
	    
	    public Order getOrderById(Long id) { 
	        Order Order = orderRepo.findOne(id);

	        if (Order == null) {
	            throw new NoSuchResourceFoundException("No Order with given id found.");
	        }

	        return Order;
	    }

	    
	    public List<Order> getAllOrders() {
	                List<Order>  OrderList =orderRepo.findAll();
	                 if (OrderList.isEmpty()) {
	             throw new NoSuchResourceFoundException("No Order with given id found.");
	         }
	                 
	                 return OrderList;

	    }
}
