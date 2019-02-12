package com.hackerrank.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Sku",schema = "public")
public class Sku {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="productName",nullable = true)
	private String productName;
	@Column(name="productLabel",nullable = true)
	private String productLabel ;
	@Column(name="inventoryOnHand",nullable = true)
	private int inventoryOnHand;
	@Column(name="minQtyReq",nullable = true)
	private int minQtyReq;
	@Column(name="price",nullable = true)
	private double price;
	public Sku(long skuId, String productName, String productLabel, int inventoryOnHand, int minQtyReq,
			double price) {
		super();
		this.id = skuId;
		this.productName = productName;
		this.productLabel = productLabel;
		this.inventoryOnHand = inventoryOnHand;
		this.minQtyReq = minQtyReq;
		this.price = price;
	}
	public Sku() {
		
	}
	public long getSkuId() {
		return id;
	}
	public void setSkuId(long skuId) {
		this.id = skuId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductLabel() {
		return productLabel;
	}
	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}
	public int getInventoryOnHand() {
		return inventoryOnHand;
	}
	public void setInventoryOnHand(int inventoryOnHand) {
		this.inventoryOnHand = inventoryOnHand;
	}
	public int getMinQtyReq() {
		return minQtyReq;
	}
	public void setMinQtyReq(int minQtyReq) {
		this.minQtyReq = minQtyReq;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
