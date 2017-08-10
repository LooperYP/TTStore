package com.ithiema.store.web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ithiema.store.domain.Order;
import com.ithiema.store.service.OrderService;
import com.ithiema.store.service.Imp.OrderServiceImp;

public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String showDetailOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String oid = request.getParameter("oid");
			OrderService service = new OrderServiceImp();
			Order order = service.showDetailOrder(oid);
			
			request.setAttribute("list", order.getList());
			return "/admin/order/orderItem.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String updateOrderState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String state = request.getParameter("state");
			String oid = request.getParameter("oid");
			OrderService service = new OrderServiceImp();
			List<Order> list = service.updateOrderState(state,oid);
			
			request.setAttribute("list", list);
			return "/admin/order/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String showOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String state = request.getParameter("state");
			
			OrderService service = new OrderServiceImp();
			List<Order> list = service.showOrder(state);
			
			request.setAttribute("list", list);
			return "/admin/order/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
