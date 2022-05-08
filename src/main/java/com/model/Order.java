package com.model;

import java.util.List;

public class Order {
	private String email;
	private String orderDate;
	private List<OrderItem> orders;
	private double total;
	
	public Order(String email, String orderDate, List<OrderItem> orders) {
		super();
		this.email = email;
		this.orderDate = orderDate;
		this.orders = orders;
		this.total = calculateTotal(orders);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItem> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderItem> orders) {
		this.orders = orders;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double calculateTotal(List<OrderItem> orderItems) {
		double total = orderItems.stream()
				.mapToDouble(OrderItem::getSubTotal)
				.sum();
		
		return total;
	}
}
