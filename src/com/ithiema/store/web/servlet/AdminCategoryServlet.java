package com.ithiema.store.web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ithiema.store.domain.Category;
import com.ithiema.store.service.CategoryService;
import com.ithiema.store.service.Imp.CategoryServiceImp;
import com.ithiema.store.utils.UUIdUtils;

public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findAllCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CategoryService categoryService = new CategoryServiceImp();
			String json = categoryService.findAllCategory();

			Gson gson = new Gson();
			List<Category> list = gson.fromJson(json, new TypeToken<List<Category>>() {
			}.getType());

			request.setAttribute("list", list);
			return "/admin/category/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String editSendCidCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		request.setAttribute("cid", cid);
		return "/admin/category/edit.jsp";
	}
	
	
	public void editCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cname = request.getParameter("cname");
			String cid = request.getParameter("cid");
			
			CategoryService categoryService = new CategoryServiceImp();
			categoryService.editCategory(cid,cname);
			
			response.sendRedirect(request.getContextPath()+"/adminCategory?action=findAllCategory");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cid = request.getParameter("cid");
			
			CategoryService categoryService = new CategoryServiceImp();
			categoryService.delCategory(cid);
			
			response.sendRedirect(request.getContextPath()+"/adminCategory?action=findAllCategory");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cname = request.getParameter("cname");
			String cid = UUIdUtils.getID();
			
			CategoryService categoryService = new CategoryServiceImp();
			categoryService.addCategory(cid,cname);
			
			response.sendRedirect(request.getContextPath()+"/adminCategory?action=findAllCategory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
