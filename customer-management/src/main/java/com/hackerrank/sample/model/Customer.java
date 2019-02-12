package com.hackerrank.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer",schema = "public")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = true)
	private String customerName;
	@Column(nullable = true)
	private Long contactNumber;
	@Column(nullable = true)
	private String address;
	@Column(nullable = true)
	private String gender;
	public long getCustomerId() {
		return id;
	}
	public void setCustomerId(long customerId) {
		this.id = customerId;
	}
	public Customer(long customerId, String customerName, Long contactNumber, String address, String gender) {
		super();
		this.id = customerId;
		this.customerName = customerName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.gender = gender; 
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Customer() {

	}
	
}
