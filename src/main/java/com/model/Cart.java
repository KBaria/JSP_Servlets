package com.model;

import java.sql.SQLException;

import com.database.ProductDAO;

public class Cart {
	private int id;
	private String email;
	private int productId;
	private int qty;
	private Product product;
	
	public Cart(String email, int productId, int qty) {
		super();
		this.email = email;
		this.productId = productId;
		this.qty = qty;
		
		try {
			this.product = ProductDAO.getProduct(productId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Cart(int id, String email, int productId, int qty) {
		super();
		this.id = id;
		this.email = email;
		this.productId = productId;
		this.qty = qty;
		
		try {
			this.product = ProductDAO.getProduct(productId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
