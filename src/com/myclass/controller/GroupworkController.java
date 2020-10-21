package com.myclass.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dao.GroupworkDao;
import com.myclass.dto.GroupworkDto;
import com.myclass.dto.RoleDto;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Groupwork;
import com.myclass.service.GroupworkService;
import com.myclass.service.TaskService;

@WebServlet(urlPatterns = { "/groupwork", "/groupwork/add", "/groupwork/edit", "/groupwork/delete",
		"/groupwork/details" })
public class GroupworkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupworkService groupworkService = null;
	private TaskService taskService = null;

	public GroupworkController() {
		// TODO Auto-generated constructor stub
		groupworkService = new GroupworkService();
		taskService = new TaskService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		HttpSession session = req.getSession();
		UserDto dto = (UserDto) session.getAttribute("USER");
		switch (action) {
		case "/groupwork":
			List<GroupworkDto> listGroupwork = null;
			//Nếu là ROLE_ADMIN thì hiện thị toàn bộ dự án
			if (dto.getRoleName().equals("ROLE_ADMIN")) {
				listGroupwork = groupworkService.getAll();
			}
			//Nếu là ROLE_MANAGER thì hiện thị dự án mà manager đó quản lý
			else {
				listGroupwork = groupworkService.getAllByRole(dto.getId());
			}
			req.setAttribute("groupworks", listGroupwork);
			req.getRequestDispatcher("/WEB-INF/views/groupwork/index.jsp").forward(req, resp);
			break;
		case "/groupwork/add":
			req.getRequestDispatcher("/WEB-INF/views/groupwork/add.jsp").forward(req, resp);
			break;
		case "/groupwork/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			GroupworkDto groupwork = groupworkService.getById(id);
			req.setAttribute("groupwork", groupwork);
			req.getRequestDispatcher("/WEB-INF/views/groupwork/edit.jsp").forward(req, resp);
			break;
		case "/groupwork/details":
			id = Integer.valueOf(req.getParameter("id"));
			List<TaskDto> tasks = null;
			List<Float> percents = null;//Danh sách phần trăm trạng thái của dự án
			tasks = taskService.getAllDtos();
			percents = groupworkService.getAllPercent(id);
			groupwork = groupworkService.getById(id);
			req.setAttribute("unfulfillPercent", percents.get(0));//Chưa hoàn thành
			req.setAttribute("processPercent", percents.get(1));//Đang thực hiện
			req.setAttribute("completePercent", percents.get(2));//Đã hoàn thành
			req.setAttribute("tasks", tasks);
			req.setAttribute("groupwork", groupwork);
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		String name = req.getParameter("name");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = null;
		Date endDate = null;

		try {
			// Định dạng lại ngày cho đúng format dd/MM/yyyy
			startDate = df.parse(req.getParameter("start_date"));
			endDate = df.parse(req.getParameter("end_date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GroupworkDto dto = new GroupworkDto();
		dto.setName(name);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);

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
