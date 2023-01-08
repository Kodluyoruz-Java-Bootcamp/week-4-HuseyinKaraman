package com.emlakcepte.client.model;

public class Order {
	
	private Integer userId;
	private String name;
	private String email; 
	private Integer quantity;
		
	public Order() {}
	
	public Order(Integer userId, String name, String email, Integer quantity) {
		this.userId = userId;	
		this.name = name;
		this.email = email;
		this.quantity = quantity;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
