package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.OrderItem;

public class OrderItemDAO {
	public static List<OrderItem> getAllOrders(String email) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String selectQuery = "select * from order_detail where email = ? order by order_date desc";
		
		PreparedStatement ps = connection.prepareStatement(selectQuery);
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		
		List<OrderItem> orders = new ArrayList<>();
		
		while(rs.next()) {
			orders.add(generateOrderItemObject(rs));
		}
		
		ps.close();
		connection.close();
		
		if(orders.size() > 0) {
			return orders;
		}else {
			return null;
		}
	}
	
	public static void insertOrder(OrderItem order) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String insertQuery = "insert into order_detail (email, order_date, product_id, qty) values (?, ?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(insertQuery);
		ps.setString(1, order.getEmail());
		ps.setString(2, order.getOrderDate());
		ps.setInt(3, order.getProductId());
		ps.setInt(4, order.getQty());
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
	}
	
	public static OrderItem generateOrderItemObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("order_id");
		String email = rs.getString("email");
		String orderDate = rs.getString("order_date");
		int productId = rs.getInt("product_id");
		int qty = rs.getInt("qty");
		
		return new OrderItem(id, email, orderDate, productId, qty);
	}
}
