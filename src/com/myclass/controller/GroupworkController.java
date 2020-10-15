package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/groupwork","/groupwork/add","/groupwork/edit","/groupwork/details"})
public class GroupworkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
		case "/groupwork":
			req.getRequestDispatcher("/WEB-INF/views/groupwork/index.jsp").forward(req, resp);
			break;
		case"/groupwork/add":
			req.getRequestDispatcher("/WEB-INF/views/groupwork/add.jsp").forward(req, resp);
			break;
		case"/groupwork/edit":
			req.getRequestDispatcher("/WEB-INF/views/groupwork/edit.jsp").forward(req, resp);
			break;
		case"/groupwork/details":
			req.getRequestDispatcher("/WEB-INF/views/groupwork/details.jsp").forward(req, resp);
			break;
		}
	}
	
}
