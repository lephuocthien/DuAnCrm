package com.myclass.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.GroupworkDto;
import com.myclass.dto.StatusDto;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Status;
import com.myclass.service.GroupworkService;
import com.myclass.service.TaskService;
import com.myclass.service.UserService;

@WebServlet(urlPatterns = { "/profile", "/profile/edit" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskService taskService = null;

	public ProfileController() {
		taskService = new TaskService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		HttpSession session = req.getSession();
		UserDto dto = (UserDto) session.getAttribute("USER");

		switch (action) {
		case "/profile":
			List<TaskDto> tasks = null;
			List<Float> percents = null;
			tasks = taskService.getAllDtosByUser(dto.getId());
			percents = taskService.getAllPercent(dto.getId());//Danh sách phần trăm trạng thái công việc của user đang đăng nhập
			req.setAttribute("unfulfillPercent", percents.get(0));//Chưa hoàn thành
			req.setAttribute("processPercent", percents.get(1));//Đang thực hiện
			req.setAttribute("completePercent", percents.get(2));//Đã hoàn thành
			req.setAttribute("user", dto);
			req.setAttribute("tasks", tasks);
			req.getRequestDispatcher("/WEB-INF/views/profile/index.jsp").forward(req, resp);
			break;
		case "/profile/edit":
			List<StatusDto> statuss = null;
			int id = Integer.valueOf(req.getParameter("id"));
			TaskDto task = taskService.getDtoById(id);
			statuss = taskService.getAllStatus();
			req.setAttribute("task", task);
			req.setAttribute("statuss", statuss);
			req.getRequestDispatcher("/WEB-INF/views/profile/edit.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int statusId = Integer.valueOf(req.getParameter("status_id"));
		int id = Integer.valueOf(req.getParameter("id"));
		TaskDto dto = new TaskDto();
		dto.setStatusId(statusId);
		dto.setId(id);
		taskService.editStatus(dto);
		resp.sendRedirect(req.getContextPath() + "/profile");
	}

}
