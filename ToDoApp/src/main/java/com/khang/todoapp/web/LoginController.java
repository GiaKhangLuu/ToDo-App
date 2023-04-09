package com.khang.todoapp.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.khang.todoapp.dao.*;
import com.khang.todoapp.model.*;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private LoginDAO login_dao;
	
	public void init() {
		login_dao = new LoginDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("Hello from login-module app");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		try {
			if (login_dao.validate(loginBean)) {
				RequestDispatcher rd = request.getRequestDispatcher("todo/todo-list.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/login/login.jsp");
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
