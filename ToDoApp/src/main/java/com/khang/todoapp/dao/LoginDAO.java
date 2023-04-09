package com.khang.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.khang.todoapp.model.LoginBean;
import com.khang.todoapp.utils.JDBCUtils;

public class LoginDAO {
	public boolean validate(LoginBean login_bean) {
		boolean status = false;
		
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("Select * from"
					+ " users where username=? and password=?");
			preparedStatement.setString(1, login_bean.getUsername());
			preparedStatement.setString(2, login_bean.getPassword());
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
