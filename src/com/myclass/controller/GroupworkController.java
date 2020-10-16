package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.GroupworkDto;
import com.myclass.dto.RoleDto;
import com.myclass.service.GroupworkService;
@WebServlet(urlPatterns = {"/groupwork","/groupwork/add","/groupwork/edit","/groupwork/details"})
public class GroupworkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupworkService  groupworkService = null;
	
	public GroupworkController() {
		groupworkService = new GroupworkService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
		case "/groupwork":
			List<GroupworkDto> listGroupworks = groupworkService.getAll();
			req.setAttribute("groupworks", listGroupworks);
			req.getRequestDispatcher("/WEB-INF/views/groupwork/index.jsp").forward(req, resp);
			break;
		case"/groupwork/add":
			req.getRequestDispatcher("/WEB-INF/views/groupwork/add.jsp").forward(req, resp);
			break;
		case"/groupwork/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			GroupworkDto groupwork = groupworkService.getById(id);
			req.setAttribute("groupwork", groupwork);
			req.getRequestDispatcher("/WEB-INF/views/groupwork/edit.jsp").forward(req, resp);
			break;
		case"/groupwork/details":
			req.getRequestDispatcher("/WEB-INF/views/groupwork/details.jsp").forward(req, resp);
			break;
		case "/groupwork/delete":
			int idDel = Integer.valueOf(req.getParameter("id"));
			groupworkService.removeById(idDel);
			resp.sendRedirect(req.getContextPath() + "/groupwork");
			return;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getServletPath();
		
		String name = req.getParameter("name");
		String startDay = req.getParameter("startDay");
		String endDay = req.getParameter("endDay");
		
		GroupworkDto dto = new GroupworkDto();
		
		dto.setName(name);
		dto.setStartDay(startDay);
		dto.setEndDay(endDay);
		
		switch (action) {
		case "/groupwork/add":
			groupworkService.add(dto);
			break;
		case "/groupwork/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			dto.setId(id);
			groupworkService.edit(dto);
			break;
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + "/groupwork");
	}
}
