package com.ithiema.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ithiema.store.domain.TestBean;


public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TestBean bean = new TestBean();
		bean.setBl(false);
		bean.setI(1);
		bean.setStr("str");
		bean.setTest("test");
		
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/Test1.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
