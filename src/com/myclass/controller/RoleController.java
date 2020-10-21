package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dao.RoleDao;
import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.service.RoleService;

@WebServlet(name = "RoleServlet", urlPatterns = { "/role", "/role/add", "/role/edit", "/role/delete" })
public class RoleController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RoleService roleService = null;

	public RoleController() {
		roleService = new RoleService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/role":
			List<RoleDto> listRole = roleService.getAll();
			req.setAttribute("roles", listRole);
			req.getRequestDispatcher("/WEB-INF/views/role/index.jsp").forward(req, resp);
			break;
		case "/role/add":
			req.getRequestDispatcher("/WEB-INF/views/role/add.jsp").forward(req, resp);
			break;
		case "/role/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			RoleDto role = roleService.getById(id);
			req.setAttribute("role", role);
			req.getRequestDispatcher("/WEB-INF/views/role/edit.jsp").forward(req, resp);
			break;
		case "/role/delete":
			int idDel = Integer.valueOf(req.getParameter("id"));
			roleService.removeById(idDel);
			resp.sendRedirect(req.getContextPath() + "/role");
			return;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String action = req.getServletPath();
		String name = req.getParameter("name");
		String desc = req.getParameter("desc");

		RoleDto dto = new RoleDto();
		dto.setName(name);
		dto.setDesc(desc);
		
		switch (action) {
		case "/role/add":
			roleService.add(dto);
			break;
		case "/role/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			dto.setId(id);
			roleService.edit(dto);
			break;
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + "/role");
	}
}
