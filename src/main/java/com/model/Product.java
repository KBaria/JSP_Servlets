package com.model;

import com.database.CategoryDAO;

public class Product {
	private int id;
	private String name;
	private String description;
	private double price;
	private int categoryId;
	private Category category;
	
	public Product(int id, String name, String description, double price, int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
		this.category = CategoryDAO.getCategory(categoryId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
