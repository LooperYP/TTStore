package com.ithiema.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import com.ithiema.store.domain.User;
import com.ithiema.store.service.UserService;
import com.ithiema.store.service.Imp.UserServiceImp;
import com.ithiema.store.utils.ConfirmCodeUtils;
import com.ithiema.store.utils.CookieUtils;
import com.ithiema.store.utils.DateConverterUtils;
import com.ithiema.store.utils.MailUtils;
import com.ithiema.store.utils.StringIsNull;
import com.ithiema.store.utils.UUIdUtils;

import cn.dsna.util.images.ValidateCode;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String registUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
//			校验验证码
			int confirmCode = ConfirmCodeUtils.confirmCode(request, response);
			if (confirmCode==0) {
				return "/jsp/register.jsp";
			}
			
			// 1.0封装参数
			User user = new User();
			// 1.1更改Date格式
			ConvertUtils.register(new DateConverterUtils(), Date.class);
			// 1.2封装参数
			BeanUtils.populate(user, request.getParameterMap());
			// 1.3封装uid,code
			String uid = UUIdUtils.getID();
			String code = UUIdUtils.getID();
			user.setUid(uid);
			user.setCode(code);
			// 2.0向数据库中插入数据
			UserService service = new UserServiceImp();
			service.registUser(user);
			// 3.0发送邮件,激活帐号
			String emailBody = "亲爱的" + user.getName()
					+ ",您好!请点击<a href='http://localhost:8080/day29_TTstore/user?action=activeUser&activeCode="
					+ user.getCode() + "'>链接</a>激活您的帐号.";
			MailUtils.sendMail(user.getEmail(), emailBody);
			// 注册成功,转发
			String path = "/jsp/regist_success.jsp";
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String activeUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String code = request.getParameter("activeCode");

			UserService service = new UserServiceImp();
			service.activeUser(code);

			String path = "/jsp/active_success.jsp";
			return path;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void generalCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ValidateCode code = new ValidateCode(120, 30, 4, 20);
		String codeServer = code.getCode();
		
		System.out.println(codeServer);
		request.getSession().setAttribute("codeServer", codeServer);
		code.write(response.getOutputStream());
	}

	public String loginUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String codeUser = request.getParameter("codeUser");
			String codeServer = (String) session.getAttribute("codeServer");
			session.removeAttribute("codeServer");
//			1.0验证验证码
			if (StringIsNull.isNull(codeUser) || StringIsNull.isNull(codeServer) || !codeServer.equalsIgnoreCase(codeUser)) {
				request.setAttribute("msg", "验证码错误");
				return "/jsp/login.jsp";
			}
			
//			2.0记住登录状态
			String username = request.getParameter("username");
			String password = request.getParameter("password");
//			待修改
//			2.1是否自动登录
			String autoLogin = request.getParameter("autoLogin");
			CookieUtils.cookieSelected(request, response, autoLogin, "autoLoginUsername", username);
			CookieUtils.cookieSelected(request, response, autoLogin, "autoLoginPassword", password);
//			2.2是否记住用户名
			String reserveUsername = request.getParameter("reserveUsername");
			CookieUtils.cookieSelected(request, response, reserveUsername, "reserveUsername", username);
			
//			3.0登录
			UserService service = new UserServiceImp();
			User user = service.loginUser(username,password);
			
			if (user==null) {
				request.setAttribute("msg", "用户名或密码错误");
			}else {
				int state = user.getState();
				if (state==0) {
					request.setAttribute("msg", "帐号未激活");
				}else if (state==1) {
					session.setAttribute("user", user);
					return "/index.jsp";
				}
			}
			return "/jsp/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void logoff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (CookieUtils.cookieIsExist(request,"autoLoginUsername")) {
			Cookie autoLoginUsername = new Cookie("autoLoginUsername", "autoLoginUsername");
			autoLoginUsername.setPath("/");
			autoLoginUsername.setMaxAge(0);
			response.addCookie(autoLoginUsername);
		}
		if (CookieUtils.cookieIsExist(request,"autoLoginPassword")) {
			Cookie autoLoginPassword = new Cookie("autoLoginPassword", "autoLoginPassword");
			autoLoginPassword.setPath("/");
			autoLoginPassword.setMaxAge(0);
			response.addCookie(autoLoginPassword);
		}
		
		session.removeAttribute("user");
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
}
