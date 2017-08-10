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
import com.ithiema.store.service.UserService;
import com.ithiema.store.service.Imp.UserServiceImp;
import com.ithiema.store.utils.CookieUtils;

public class AutoLoginFilter implements Filter {

    public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest r, ServletResponse p, FilterChain chain) throws IOException, ServletException {
//		1.0强制类型转换
		HttpServletRequest request = (HttpServletRequest) r;
		HttpServletResponse response = (HttpServletResponse) p;
		try {
//		2.0业务逻辑
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
//			2.1自动登录
			if (user==null) {
				if (CookieUtils.cookieIsExist(request, "autoLoginUsername")&&CookieUtils.cookieIsExist(request, "autoLoginPassword")) {
					String autoLoginUsername = CookieUtils.getCookieValue(request, "autoLoginUsername");
					String autoLoginPassword = CookieUtils.getCookieValue(request, "autoLoginPassword");
					
					UserService service = new UserServiceImp();
					user = service.loginUser(autoLoginUsername,autoLoginPassword);
					session.setAttribute("user", user);
				}
			}
//			2.2记住用户名
			if (CookieUtils.cookieIsExist(request, "reserveUsername")) {
				String reserveUsername = CookieUtils.getCookieValue(request, "reserveUsername");
				request.setAttribute("reserveUsername", reserveUsername);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		3.0放行
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
