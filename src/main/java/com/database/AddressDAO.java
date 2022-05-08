package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Address;

public class AddressDAO {
	public static Address getAddress(String email) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String selectQuery = "select * from user_address where email = ?";
		
		PreparedStatement ps = connection.prepareStatement(selectQuery);
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		Address userAddress = null;
		
		if(rs.next()) {
			userAddress = generateAddressObj(rs);
		}
		
		ps.close();
		connection.close();
		
		return userAddress;
	}
	
	public static void insertAddress(Address address) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String updateQuery = "insert into user_address values (?, ?, ?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(updateQuery);
		ps.setString(1, address.getEmail());
		ps.setString(2, address.getAddress());
		ps.setString(3, address.getState());
		ps.setString(4, address.getCity());
		ps.setString(5, address.getPin());
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
	}
	
	public static void updateAddress(Address address) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String updateQuery = "update user_address set address = ?, state = ?, city = ?, pin = ? where email = ?";
		
		PreparedStatement ps = connection.prepareStatement(updateQuery);
		ps.setString(1, address.getAddress());
		ps.setString(2, address.getState());
		ps.setString(3, address.getCity());
		ps.setString(4, address.getPin());
		ps.setString(5, address.getEmail());
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
		
	}
	
	public static Address generateAddressObj(ResultSet rs) throws SQLException {
		String email = rs.getString("email");
		String address = rs.getString("address");
		String state = rs.getString("state");
		String city = rs.getString("city");
		String pin = rs.getString("pin");
		
		return new Address(email, address, state, city, pin);
	}
}
