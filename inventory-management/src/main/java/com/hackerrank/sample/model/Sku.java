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
	@Column(name="product_Name",nullable = true)
	private String product_Name;
	@Column(name="product_Label",nullable = true)
	private String product_Label ;
	@Column(name="inventory_On_Hand",nullable = true)
	private int inventory_On_Hand;
	@Column(name="min_Qty_Req",nullable = true)
	private int min_Qty_Req;
	@Column(name="price",nullable = true)
	private double price;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProduct_Name() {
		return product_Name;
	}
	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}
	public String getProduct_Label() {
		return product_Label;
	}
	public void setProduct_Label(String product_Label) {
		this.product_Label = product_Label;
	}
	public int getInventory_On_Hand() {
		return inventory_On_Hand;
	}
	public void setInventory_On_Hand(int inventory_On_Hand) {
		this.inventory_On_Hand = inventory_On_Hand;
	}
	public int getMin_Qty_Req() {
		return min_Qty_Req;
	}
	public void setMin_Qty_Req(int min_Qty_Req) {
		this.min_Qty_Req = min_Qty_Req;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Sku() {
		// TODO Auto-generated constructor stub
	}
	public Sku(long id, String product_Name, String product_Label, int inventory_On_Hand, int min_Qty_Req,
			double price) {
		this.id = id;
		this.product_Name = product_Name;
		this.product_Label = product_Label;
		this.inventory_On_Hand = inventory_On_Hand;
		this.min_Qty_Req = min_Qty_Req;
		this.price = price;
	}
	
	
}
