package com.ithiema.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ithiema.store.service.CategoryService;
import com.ithiema.store.service.Imp.CategoryServiceImp;

public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void findAllCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CategoryService categoryService = new CategoryServiceImp();
			String json = categoryService.findAllCategory();
			
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
