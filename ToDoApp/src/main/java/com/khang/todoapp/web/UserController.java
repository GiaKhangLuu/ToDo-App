package com.khang.todoapp.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.khang.todoapp.model.*;
import com.khang.todoapp.dao.*;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private UserDAO user_dao;
	
	public void init() {
		user_dao = new UserDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("register/register.jsp");
		response.getWriter().println("hahaha");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		register(request, response);
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setFirst_name(firstName);
		user.setLast_name(lastName);
		user.setUsername(userName);
		user.setPassword(password);
		
		try {
			int result = user_dao.registerUser(user);
			if (result == 1) {
                request.setAttribute("NOTIFICATION", "User Registered Successfully!");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("register/register.jsp");
		rd.forward(request, response);
	}

}
