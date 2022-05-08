package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Category;

public class CategoryDAO {
	public static Category getCategory(int id) {
		Connection connection = ConnectionProvider.getConnection();
		String selectQuery = "select * from category where id = ?";
		
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(selectQuery);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return generateCategoryObj(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}
	
	public static Category generateCategoryObj(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		
		return new Category(id, name, description);
	}
}
