package com.ithiema.store.web.filter;

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

import com.ithiema.store.domain.User;

/**
 * Servlet Filter implementation class AccessUserFilter
 */
public class AccessUserFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user==null) {
			request.setAttribute("msg", "请登录后操作!");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
