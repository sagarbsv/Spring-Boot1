package com.tcs.hack.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderLineItem")
public class OrderLineItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderLineItemId;
	
	@Column(name = "skuId", nullable = false)
	private long skuId;
	@ManyToOne
	@JoinColumn(name = "orderId", nullable = false)
	private Order order;
	@Column(name = "itemQty", nullable = false)
	private int itemQty;
	public long getOrderLineItemId() {
		return orderLineItemId;
	}
	public void setOrderLineItemId(long orderLineItemId) {
		this.orderLineItemId = orderLineItemId;
	}
	public long getSkuId() {
		return skuId;
	}
	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	public OrderLineItem(long orderLineItemId, long skuId, Order order, int itemQty) {
		this.orderLineItemId = orderLineItemId;
		this.skuId = skuId;
		this.order = order;
		this.itemQty = itemQty;
	}
	public OrderLineItem() {
	}
	

}
