package com.tcs.hack.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	@Column(name = "customerId", nullable = false)
	private long customerId;
	@Column(nullable=true)
	private String paymentChannel;
	@Column(nullable=true)
	private boolean isCod;
	@Column(nullable=true)
	private String orderStatus;
	@Column(nullable=true)
	private long OrderCreatedOn;
	@Column(nullable=true)
	private double totalAmount;
	@Column(nullable=true)
	private String shippingAddress;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getPaymentChannel() {
		return paymentChannel;
	}
	public void setPaymentChannel(String paymentChannel) {
		this.paymentChannel = paymentChannel;
	}
	public boolean isCod() {
		return isCod;
	}
	public void setCod(boolean isCod) {
		this.isCod = isCod;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public long getOrderCreatedOn() {
		return OrderCreatedOn;
	}
	public void setOrderCreatedOn(long orderCreatedOn) {
		OrderCreatedOn = orderCreatedOn;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Order(long orderId, long customerId, String paymentChannel, boolean isCod, String orderStatus,
			long orderCreatedOn, double totalAmount, String shippingAddress) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.paymentChannel = paymentChannel;
		this.isCod = isCod;
		this.orderStatus = orderStatus;
		OrderCreatedOn = orderCreatedOn;
		this.totalAmount = totalAmount;
		this.shippingAddress = shippingAddress;
	}
	public Order() {
		
	}
	
	
	

}
