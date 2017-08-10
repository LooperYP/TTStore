package com.ithiema.store.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmCodeUtils {
	
	public static int confirmCode(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		String codeUser = request.getParameter("codeUser");
		String codeServer = (String) session.getAttribute("codeServer");
		session.removeAttribute("codeServer");

		if (StringIsNull.isNull(codeUser) || StringIsNull.isNull(codeServer) || !codeServer.equalsIgnoreCase(codeUser)) {
			request.setAttribute("msg", "验证码错误");
			return 0;
		}
		
		return 1;
	}
}
