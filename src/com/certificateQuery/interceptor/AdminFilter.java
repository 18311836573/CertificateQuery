package com.certificateQuery.interceptor;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminFilter
 */
public class AdminFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			String login  = (String)session.getAttribute("login");
			System.out.println(login);
			if(login == null || !login.equals("true")) {
				System.out.println("尚未登录");
				res.sendRedirect("http://"+req.getHeader("Host")+"/certificateQuery-SSM/index.html");
			}
			else {
				System.out.println("已经登录");
				chain.doFilter(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("调用adminfilter");
	}
}
