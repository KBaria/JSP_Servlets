package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;

public class UserDAO {
	public static User getUser(String email, String password) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String selectQuery = "select * from user where email = ? and password = ?";
		
		PreparedStatement ps = connection.prepareStatement(selectQuery);
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			User user = generateUserObj(rs);
			return user;
		}else {
			return null;
		}
	}
	
	public static User getUser(String email) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String selectQuery = "select * from user where email = ?";
		
		PreparedStatement ps = connection.prepareStatement(selectQuery);
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			User user = generateUserObj(rs);
			return user;
		}else {
			return null;
		}
	}
	
	public static void insertUser(User user) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String insertQuery = "insert into user values(?, ?, ?, ?, ?)";
		
		PreparedStatement ps = connection.prepareStatement(insertQuery);
		ps.setString(1, user.getEmail());
		ps.setString(2, user.getFirstName());
		ps.setString(3, user.getLastName());
		ps.setString(4, user.getPassword());
		ps.setString(5, user.getContact());
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
	}
	
	public static void updateUser(User user) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String updateQuery = "update user set first_name = ?, last_name = ?, password = ?, contact = ? where email = ?";
		
		PreparedStatement ps = connection.prepareStatement(updateQuery);
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getContact());
		ps.setString(5, user.getEmail());
		
		ps.executeUpdate();
		
		ps.close();
		connection.close();
	}
	
	public static boolean checkEmailValidity(String email) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String insertQuery = "select * from user where email = ?";
		
		PreparedStatement ps = connection.prepareStatement(insertQuery);
		ps.setString(1, email);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean checkContactValidity(String contact) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();
		String insertQuery = "select * from user where contact = ?";
		
		PreparedStatement ps = connection.prepareStatement(insertQuery);
		ps.setString(1, contact);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return false;
		}else {
			return true;
		}
	}
	
	private static User generateUserObj(ResultSet rs) throws SQLException {
		String email = rs.getString("email");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String password = rs.getString("password");
		String contact = rs.getString("contact");
		
		return new User(email, firstName, lastName, password, contact);
	}
}
