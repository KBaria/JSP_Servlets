package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Cart;

public class CartDAO {
	public static List<Cart> getCart(String email) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String selectQuery = "select * from cart where email = ?";
		
		PreparedStatement ps = connection.prepareStatement(selectQuery);
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		
		List<Cart> cartItems = new ArrayList<>();
		while(rs.next()) {
			cartItems.add(generateCartObj(rs));
		}
		
		if(cartItems.size() > 0) {
			return cartItems;
		}else {
			return null;
		}
	}
	
	public static void addToCart(Cart cart) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String selectQuery = "insert into cart (email, product_id, qty) values (?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(selectQuery);
		ps.setString(1, cart.getEmail());
		ps.setInt(2, cart.getProductId());
		ps.setInt(3, cart.getQty());
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
	}
	
	public static void updateCart(Cart cart) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String updateQuery = "update cart set qty = ? where email = ? and product_id = ?";
		
		PreparedStatement ps = connection.prepareStatement(updateQuery);
		ps.setInt(1, cart.getQty());
		ps.setString(2, cart.getEmail());
		ps.setInt(3, cart.getProductId());
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
	}
	
	public static void removeFromCart(String email, int productId) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String deleteQuery = "delete from cart where email = ? and product_id = ?";
		
		PreparedStatement ps = connection.prepareStatement(deleteQuery);
		ps.setString(1, email);
		ps.setInt(2, productId);
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
	}
	
	public static void removeFromCart(String email) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String deleteQuery = "delete from cart where email = ?";
		
		PreparedStatement ps = connection.prepareStatement(deleteQuery);
		ps.setString(1, email);
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
	}
	
	public static Cart generateCartObj(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String email = rs.getString("email");
		int productId = rs.getInt("product_id");
		int qty = rs.getInt("qty");
		
		return new Cart(id, email, productId, qty);
	}
}
