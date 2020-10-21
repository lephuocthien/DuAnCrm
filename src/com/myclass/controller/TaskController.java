package com.myclass.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dao.TaskDao;
import com.myclass.dto.GroupworkDto;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.service.GroupworkService;
import com.myclass.service.TaskService;
import com.myclass.service.UserService;

@WebServlet(urlPatterns = { "/task", "/task/add", "/task/edit", "/task/delete" })
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskService taskService = null;
	private GroupworkService groupworkService = null;
	private UserService userService = null;

	public TaskController() {
		taskService = new TaskService();
		groupworkService = new GroupworkService();
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		HttpSession session = req.getSession();
		UserDto dto = (UserDto) session.getAttribute("USER");
		
		switch (action) {
		case "/task":
			List<TaskDto> tasks = null;
			//Nếu là ROLE_ADMIN thì hiện thị toàn bộ công việc
			if (dto.getRoleName().equals("ROLE_ADMIN")) {
				tasks = taskService.getAllDtos();
			}
			//Nếu là ROLE_MANAGER thì hiện thị công việc do manager quản lý
			else if (dto.getRoleName().equals("ROLE_MANAGER")) {
				tasks = taskService.getAllDtosByManager(dto.getId());
			} 
			//Nếu là ROLE_USER thì hiện thị công việc do user quản lý
			else {
				tasks = taskService.getAllDtosByUser(dto.getId());
			}
			req.setAttribute("tasks", tasks);
			req.getRequestDispatcher("/WEB-INF/views/task/index.jsp").forward(req, resp);
			break;
		case "/task/add":
			List<UserDto> users = null;
			List<GroupworkDto> groupworks = null;
			//Nếu là ROLE_ADMIN thì có thể thêm công việc cho toàn bộ nhân viên cảu tất cả dự án
			if (dto.getRoleName().equals("ROLE_ADMIN")) {
				groupworks = groupworkService.getAll();
				users = userService.getAll();
			} 
			//Nếu là ROLE_MANAGER thì chỉ có thể thêm công việc cho toàn bộ nhân viên là ROLE_USER của dự án mà manager đó quản lý
			else {
				groupworks = groupworkService.getAllByRole(dto.getId());
				users = userService.getAllUser();
			}
			req.setAttribute("groupworks", groupworks);
			req.setAttribute("users", users);
			req.getRequestDispatcher("/WEB-INF/views/task/add.jsp").forward(req, resp);
			break;
		case "/task/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			TaskDto task = taskService.getById(id);
			//Nếu là ROLE_ADMIN thì có thể thêm công việc cho toàn bộ nhân viên cảu tất cả dự án
			if (dto.getRoleName().equals("ROLE_ADMIN")) {
				groupworks = groupworkService.getAll();
				users = userService.getAll();
			} 
			//Nếu là ROLE_MANAGER thì chỉ có thể thêm công việc cho toàn bộ nhân viên là ROLE_USER của dự án mà manager đó quản lý
			else {
				groupworks = groupworkService.getAllByRole(dto.getId());
				users = userService.getAllUser();
			}
			req.setAttribute("task", task);
			req.setAttribute("groupworks", groupworks);
			req.setAttribute("users", users);
			req.getRequestDispatcher("/WEB-INF/views/task/edit.jsp").forward(req, resp);
			break;
		case "/task/delete":
			int idDel = Integer.valueOf(req.getParameter("id"));
			taskService.removeById(idDel);
			resp.sendRedirect(req.getContextPath() + "/task");
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String name = req.getParameter("name");
		Date startDate = null;
		Date endDate = null;
		int groupworkId = Integer.valueOf(req.getParameter("groupwork_id"));
		int userId = Integer.valueOf(req.getParameter("user_id"));

		// Định dạng lại ngày cho đúng format dd/MM/yyyy
		try {
			startDate = df.parse(req.getParameter("start_date"));
			endDate = df.parse(req.getParameter("end_date"));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TaskDto dto = new TaskDto();
		dto.setName(name);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setGroupworkId(groupworkId);
		dto.setUserId(userId);

		switch (action) {
		case "/task/add":
			taskService.add(dto);
			break;
		case "/task/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			dto.setId(id);
			taskService.edit(dto);
			break;
		default:
			break;
		}
		resp.sendRedirect(req.getContextPath() + "/task");

	}

}
