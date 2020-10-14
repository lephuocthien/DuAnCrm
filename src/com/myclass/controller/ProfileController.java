package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/profile","/profile/edit"})
public class ProfileController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
		case "/profile":
			req.getRequestDispatcher("/WEB-INF/views/profile/index.jsp").forward(req, resp);
			break;
		case"/profile/edit":
			req.getRequestDispatcher("/WEB-INF/views/profile/edit.jsp").forward(req, resp);
			break;
		
		}
	}
	
}
