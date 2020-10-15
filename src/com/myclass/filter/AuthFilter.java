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
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String action = req.getServletPath();
		
		// NẾU LÀ TRANG LOGIN THÌ KHÔNG CẦN KIỂM TRA SESSION
		if(action.startsWith("/login")) {
			chain.doFilter(request, response);
		}
		else {
			// KIỂM TRA SESSION
			HttpSession session = req.getSession();
			if (session.getAttribute("USER") == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
			else {
				
				// Lấy ra thông tin user lưu trong Session
				UserDto dto = (UserDto)session.getAttribute("USER");
				
				// PHÂN QUYỀN NGƯỜI DÙNG
			
				// TH1: Nếu link bắt đầu bằng /role thì phải có roleName là ROLE_ADMIN
				String roleName = dto.getRoleName();
				if(action.startsWith("/role") && !roleName.equals("ROLE_ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/login");
					return;
				}
				
				// TH2: NẾU LÀ /user thì phải có roleName là ROLE_ADMIN hoặc ROLE_LEADER
				if(action.startsWith("/user") && !roleName.equals("ROLE_ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/login");
					return;
				}
				
				// TH3: NẾU LÀ /home thì roleName 
				// là ROLE_ADMIN hoặc ROLE_LEADER hay ROLE_USER đều vào đc
				
				chain.doFilter(request, response);
				
				
			}
		}
	}

}
