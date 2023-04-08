package com.khang.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.khang.todoapp.model.*;
import com.khang.todoapp.utils.*;

public class UserDAO {
	
	public int registerUser(User user) {
		String INSERT_USER_SQL = "INSERT INTO USERS" + 
				" (first_name, last_name, username, password) VALUES" + 
				" (?, ?, ?, ?);";
		
		int result = 0;
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER_SQL);
			preparedStatement.setString(1, user.getFirst_name());
			preparedStatement.setString(2, user.getLast_name());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());
			
			System.out.println(preparedStatement);
			
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

}
