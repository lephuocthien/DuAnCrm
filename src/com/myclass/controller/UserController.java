package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dao.RoleDao;
import com.myclass.dao.UserDao;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.service.TaskService;
import com.myclass.service.UserService;

@WebServlet(name = "UserServlet", urlPatterns = { "/user", "/user/add", "/user/edit", "/user/delete", "/user/details" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RoleDao roleDao = null;
	private UserService userService = null;
	private TaskService taskService = null;

	public UserController() {
		roleDao = new RoleDao();
		userService = new UserService();
		taskService = new TaskService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		
		switch (action) {
		case "/user":
			HttpSession session = req.getSession();
			session.removeAttribute("MESSAGE");//quay về index thì xoá session
			List<UserDto> listuUsers = userService.getAllDtos();
			req.setAttribute("users", listuUsers);
			req.getRequestDispatcher("/WEB-INF/views/user/index.jsp").forward(req, resp);
			break;
		case "/user/add":
			session = req.getSession();
			req.setAttribute("message", session.getAttribute("MESSAGE"));//Nếu user thêm bị sai thì thông báo
			req.setAttribute("roles", roleDao.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/add.jsp").forward(req, resp);
			break;
		case "/user/edit":
			session = req.getSession();
			req.setAttribute("message", session.getAttribute("MESSAGE"));//Nếu user sửa bị sai thì thông báo
			int id = Integer.valueOf(req.getParameter("id"));
			UserDto user = userService.getById(id);
			req.setAttribute("user", user);
			req.setAttribute("roles", roleDao.findAll());
			req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
			break;
		case "/user/details":
			List<TaskDto> tasks =null;
			List<Float> percents = null;
			id = Integer.valueOf(req.getParameter("id"));
			user = userService.getById(id);
			tasks = taskService.getAllDtosByUser(user.getId());
			percents = taskService.getAllPercent(user.getId());	//Danh sách phần trăm trạng thái công việc của user
			req.setAttribute("unfulfillPercent", percents.get(0));//Chưa hoàn thành
			req.setAttribute("processPercent", percents.get(1));//Đang thực hiện
			req.setAttribute("completePercent", percents.get(2));//Đã hoàn thành
			req.setAttribute("tasks", tasks);
			req.setAttribute("user", user);
			req.getRequestDispatcher("/WEB-INF/views/user/details.jsp").forward(req, resp);
			break;
		case "/user/delete":
			int idDel = Integer.valueOf(req.getParameter("id"));
			userService.removeById(idDel);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		HttpSession session = req.getSession();
		session.setAttribute("MESSAGE", null);
		
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
			//Nếu id của email đã tồn tại thì báo lỗi
			if (userService.checkEmailExists(-1,email) == false) {
				userService.save(dto);
			} else {
				session.setAttribute("MESSAGE", "Email đã tồn tại");
			}
			break;
		case "/user/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			//Nếu id và email đã tồn tại thì báo lỗi
			if (userService.checkEmailExists(id,email) == false) {			
				dto.setId(id);
				userService.edit(dto);
			} else {
				session.setAttribute("MESSAGE", "Email bị trùng");
			}
			break;
		default:
			break;
		}	
		if(session.getAttribute("MESSAGE")==null) {
			resp.sendRedirect(req.getContextPath() + "/user");
		}
		//Nếu /add bị lỗi thì sendRedirect lại /user/add vào báo lỗi
		else if (session.getAttribute("MESSAGE").equals("Email đã tồn tại")) {
			resp.sendRedirect(req.getContextPath() + "/user/add");
		}
		//Nếu /edit bị lỗi thì sendRedirect lại /user/edit vào báo lỗi
		else if(session.getAttribute("MESSAGE").equals("Email bị trùng")){
			int id = Integer.valueOf(req.getParameter("id"));
			resp.sendRedirect(req.getContextPath() + "/user/edit?id="+id);
		}
		
	}
	
}