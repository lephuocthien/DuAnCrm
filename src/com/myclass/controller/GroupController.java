package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.GroupworkDto;
import com.myclass.service.GroupworkService;

@WebServlet(name = "GroupworkServlet", urlPatterns = {"/groupwork", "/groupwork/add", "groupwork/details"})
public class GroupController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GroupworkService groupworkService = null;
	
	public GroupController() {
		groupworkService = new GroupworkService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getServletPath();
		
		switch (action) {
		case "/groupwork":
			
			break;
		case "/groupwork/add":
			
			break;
		case "/groupwork/details":
			
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
