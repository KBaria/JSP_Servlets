package com.model;

import java.sql.SQLException;

import com.database.ProductDAO;

public class OrderItem {
	private int id;
	private String email;
	private String orderDate;
	private int productId;
	private int qty;
	private Product product;
	private double subTotal;

	public OrderItem(String email, String orderDate, int productId, int qty) {
		super();
		this.email = email;
		this.orderDate = orderDate;
		this.productId = productId;
		this.qty = qty;
		
		try {
			this.product = ProductDAO.getProduct(productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.subTotal = product.getPrice() * qty;
	}
	
	public OrderItem(int id, String email, String orderDate, int productId, int qty) {
		super();
		this.id = id;
		this.email = email;
		this.orderDate = orderDate;
		this.productId = productId;
		this.qty = qty;
		
		try {
			this.product = ProductDAO.getProduct(productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.subTotal = product.getPrice() * qty;
	}



	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}
