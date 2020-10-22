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
			//Náº¿u lĂ  ROLE_ADMIN thĂ¬ hiá»‡n thá»‹ toĂ n bá»™ dá»± Ă¡n
			if (dto.getRoleName().equals("ROLE_ADMIN")) {
				listGroupwork = groupworkService.getAll();
			}
			//Náº¿u lĂ  ROLE_MANAGER thĂ¬ hiá»‡n thá»‹ dá»± Ă¡n mĂ  manager Ä‘Ă³ quáº£n lĂ½
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
			List<Float> percents = null;//Danh sĂ¡ch pháº§n trÄƒm tráº¡ng thĂ¡i cá»§a dá»± Ă¡n
			tasks = taskService.getAllDtos();
			percents = groupworkService.getAllPercent(id);
			groupwork = groupworkService.getById(id);
			
			System.out.println(percents.get(0));
			System.out.println(percents.get(1));
			System.out.println(percents.get(2));

			req.setAttribute("unfulfillPercent", percents.get(0));//ChÆ°a hoĂ n thĂ nh
			req.setAttribute("processPercent", percents.get(1));//Ä�ang thá»±c hiá»‡n
			req.setAttribute("completePercent", percents.get(2));//Ä�Ă£ hoĂ n thĂ nh
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
			// Ä�á»‹nh dáº¡ng láº¡i ngĂ y cho Ä‘Ăºng format dd/MM/yyyy
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
