package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.GroupworkDto;
import com.myclass.service.GroupworkService;

@WebServlet(urlPatterns = {"/groupwork","/groupwork/add","/groupwork/edit","/groupwork/details"})
public class GroupworkController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private GroupworkService groupworkService = null;
	
	public GroupworkController() {
		groupworkService = new GroupworkService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		int statusId = Integer.valueOf(req.getParameter("statusId"));
		int userId = Integer.valueOf(req.getParameter("userId"));
		int taskId = Integer.valueOf(req.getParameter("taskId"));
		
		GroupworkDto dto = new GroupworkDto();
		dto.setName(name);
		dto.setStartDay(startDay);
		dto.setEndDay(endDay);
		dto.setStatusId(statusId);
		dto.setUserId(userId);
		dto.setTaskId(taskId);
		
		
		switch (action) {
		case "/groupwork/add":
			
			break;

		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + "/groupwork");
	}
	
}
