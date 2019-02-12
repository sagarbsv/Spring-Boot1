package com.tcs.hack.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.hack.order.model.OrderLineItem;

public interface OrderLineItemRepo extends JpaRepository<OrderLineItem, Long>{

}
