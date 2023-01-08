package com.emlakcepte.model;

import com.emlakcepte.model.enums.OrderStatus;

public class Order {

	private Integer userId;
	private OrderStatus orderStatus;
	
	public Order() {}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", orderStatus=" + orderStatus + "]";
	}
	
}
