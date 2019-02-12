package com.hackerrank.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vendor",schema = "public")
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="vendorName",nullable = true)
	private String vendorName;
	@Column(name="vendorContactNo",nullable = true)
	private long vendorContactNo;
	@Column(name="vendorEmail",nullable = true)
	private String vendorEmail;
	@Column(name="vendorUsername",nullable = true)
	private String vendorUsername;
	@Column(name="vendorAddress",nullable = true)
	private String vendorAddress ;
	
	public Vendor(long vendorId, String vendorName, long vendorContactNo, String vendorEmail, String vendorUsername,
			String vendorAddress) {
		super();
		this.id = vendorId;
		this.vendorName = vendorName;
		this.vendorContactNo = vendorContactNo;
		this.vendorEmail = vendorEmail;
		this.vendorUsername = vendorUsername;
		this.vendorAddress = vendorAddress;
	}
	public Vendor() {
		
	}
	public long getVendorId() {
		return id;
	}
	public void setVendorId(long vendorId) {
		this.id = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public long getVendorContactNo() {
		return vendorContactNo;
	}
	public void setVendorContactNo(long vendorContactNo) {
		this.vendorContactNo = vendorContactNo;
	}
	public String getVendorEmail() {
		return vendorEmail;
	}
	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}
	public String getVendorUsername() {
		return vendorUsername;
	}
	public void setVendorUsername(String vendorUsername) {
		this.vendorUsername = vendorUsername;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	
	

}
