package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.UserDto;

public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String action = req.getServletPath();

		// NẾU LÀ TRANG LOGIN VÀ SESSION NULL THÌ KHÔNG CẦN KIỂM TRA SESSION
		if (action.startsWith("/login") && session.getAttribute("USER") == null) {
			chain.doFilter(request, response);
		}
		// Nếu đã đăng nhập thì phải đăng xuất để login
		else if (action.startsWith("/login") && session.getAttribute("USER") != null) {
			resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			// KIỂM TRA SESSION
			if (session.getAttribute("USER") == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
			} else {

				// Lấy ra thông tin user lưu trong Session
				UserDto dto = (UserDto) session.getAttribute("USER");

				// PHÂN QUYỀN NGƯỜI DÙNG

				// TH1: Nếu link bắt đầu bằng /role thì phải có roleName là ROLE_ADMIN
				String roleName = dto.getRoleName();
				if (action.startsWith("/role") && !roleName.equals("ROLE_ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/logout");
					return;
				}

				// TH2: NẾU LÀ /user và /groupwork thì phải có roleName là ROLE_ADMIN hoặc ROLE_MANAGER
				if ((action.startsWith("/user")||action.startsWith("/groupwork"))
						&& (!roleName.equals("ROLE_ADMIN") && !roleName.equals("ROLE_MANAGER"))) {
					resp.sendRedirect(req.getContextPath() + "/logout");
					return;
				}
				// TH3: NẾU LÀ /user/add và /user/edit, /user/delete thì phải có roleName là ROLE_ADMIN
				if ((action.equals("/user/add") || action.equals("/user/edit")
						|| action.equals("/user/delete") || action.equals("/user/details"))
						&& !roleName.equals("ROLE_ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/logout");
					return;
				}
				
				// TH4: NẾU LÀ /task/add, /task/edit , /task/delete thì roleName là ROLE_ADMIN và ROLE_MANAGER
				if ((action.equals("/task/add") || action.equals("/task/edit")
						|| action.equals("/task/delete"))
						&& (!roleName.equals("ROLE_ADMIN")&&!roleName.equals("ROLE_MANAGER"))) {
					resp.sendRedirect(req.getContextPath() + "/logout");
					return;
				}
				// Còn lại là ROLE_ADMIN hoặc ROLE_LEADER hay ROLE_USER đều vào đc

				chain.doFilter(request, response);

			}
		}
	}

}
