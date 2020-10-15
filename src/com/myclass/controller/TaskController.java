package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/task", "/task/add", "/task/edit" })
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String action = req.getServletPath();
		
		switch (action) {
		case "/task":
			req.getRequestDispatcher("/WEB-INF/views/task/index.jsp").forward(req, resp);
			break;
		case"/task/add":
			req.getRequestDispatcher("/WEB-INF/views/task/add.jsp").forward(req, resp);
			break;
		case"/task/edit":
			req.getRequestDispatcher("/WEB-INF/views/task/edit.jsp").forward(req, resp);
			break;
		}
	}

}
