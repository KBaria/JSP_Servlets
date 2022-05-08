package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Product;

public class ProductDAO {
	public static List<Product> getAllProducts() throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String selectQuery = "select * from product";
		
		PreparedStatement ps = connection.prepareStatement(selectQuery);
		
		ResultSet rs = ps.executeQuery();
		
		List<Product> products = new ArrayList<>();
		
		while(rs.next()) {
			products.add(generateProductObj(rs));
		}
		
		if(products.size() > 0) {
			return products;
		}else {
			return null;
		}
	}
	
	public static Product getProduct(int id) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String selectQuery = "select * from product where id = ?";
		
		PreparedStatement ps = connection.prepareStatement(selectQuery);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return generateProductObj(rs);
		}else {
			return null;
		}
	}
	
	public static Product generateProductObj(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		double price = rs.getDouble("price");
		int categoryId = rs.getInt("category_id");
		
		return new Product(id, name, description, price, categoryId);
	}
}
