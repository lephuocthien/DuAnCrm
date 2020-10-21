package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.UserDto;
import com.myclass.service.UserService;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthController extends HttpServlet {
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/login":
			HttpSession session = req.getSession();
			req.getRequestDispatcher("/WEB-INF/views/login/index.jsp")
			.forward(req, resp);
			break;
		case "/logout":
			// Xóa Session có key là USER
			session = req.getSession();
			session.removeAttribute("USER");
			// Chuyển hướng về trang login
			resp.sendRedirect(req.getContextPath() +  "/login");
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDto dto = userService.login(email, password);
		if(dto != null) {
			HttpSession session = req.getSession();
			session.setAttribute("USER", dto);
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		else {
			req.setAttribute("message", "Sai email hoặc mật khẩu!");
			req.getRequestDispatcher("/WEB-INF/views/login/index.jsp")
			.forward(req, resp);
		}
	}
}
