package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dao.RoleDao;
import com.myclass.dao.UserDao;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.service.UserService;

@WebServlet(name = "UserServlet", 
	urlPatterns = {"/user", "/user/add", "/user/edit", "/user/delete", "/user/details" })
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private RoleDao roleDao = null;
	private UserService userService = null;

	public UserController() {
		roleDao = new RoleDao();
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/user":
			List<UserDto> listuUsers = userService.getAllDtos();
			req.setAttribute("users", listuUsers);
			req.getRequestDispatcher("/WEB-INF/views/user/index.jsp").forward(req, resp);
			break;
		case "/user/add":
			req.setAttribute("roles", roleDao.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/add.jsp").forward(req, resp);
			break;
		case "/user/edit":
			int id = Integer.valueOf(req.getParameter("id"));
//			User user = userDao.findById(id);
//			req.setAttribute("user", user);
//			req.setAttribute("roles", roleDao.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
			break;
		case "/user/details":
			req.getRequestDispatcher("/WEB-INF/views/user/details.jsp").forward(req, resp);
			break;
		case "/user/delete":
			int idDel = Integer.valueOf(req.getParameter("id"));
//			userDao.deleteById(idDel);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String fullname = req.getParameter("fullname");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.valueOf(req.getParameter("roleId"));
		
		UserDto dto = new UserDto();
		dto.setEmail(email);
		dto.setPassword(password);
		dto.setFullname(fullname);
		dto.setAvatar(avatar);
		dto.setRoleId(roleId);
		
		switch (action) {
		case "/user/add":
			userService.save(dto);
			break;
		case "/user/edit":
			int id = Integer.valueOf(req.getParameter("id"));
//			userDao.update(user);
			break;
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + "/user");
	}
}
