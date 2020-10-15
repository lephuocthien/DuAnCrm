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
		
		// NẾU LÀ TRANG LOGIN KHÔNG CẦN KIỂM TRA SESSION
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
				
				// Lấy ra thông tin user lưu trong session
				UserDto dto = (UserDto)session.getAttribute("USER");
				
				// PHĂ‚N QUYá»€N NGÆ¯á»œI DĂ™NG
			
				// TH1: Náº¿u link báº¯t Ä‘áº§u báº±ng /role thĂ¬ pháº£i cĂ³ roleName lĂ  ROLE_ADMIN
				String roleName = dto.getRoleName();
				if(action.startsWith("/role") && !roleName.equals("ROLE_ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/login");
					return;
				}
				
				// TH2: Náº¾U LĂ€ /user thĂ¬ pháº£i cĂ³ roleName lĂ  ROLE_ADMIN hoáº·c ROLE_LEADER
				if(action.startsWith("/user") && !roleName.equals("ROLE_ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/login");
					return;
				}
				
				// TH3: Náº¾U LĂ€ /home thĂ¬ roleName 
				// lĂ  ROLE_ADMIN hoáº·c ROLE_LEADER hay ROLE_USER Ä‘á»�u vĂ o Ä‘c
				
				chain.doFilter(request, response);
				
				
			}
		}
	}

}
