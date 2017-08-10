package com.ithiema.store.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ithiema.store.domain.Order;
import com.ithiema.store.domain.OrderItem;
import com.ithiema.store.domain.PageBean;
import com.ithiema.store.domain.Product;
import com.ithiema.store.domain.User;
import com.ithiema.store.service.OrderService;
import com.ithiema.store.service.Imp.OrderServiceImp;
import com.ithiema.store.utils.UUIdUtils;

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	public String saveOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user==null) {
			request.setAttribute("msg", "请登录后操作!");
			return "/jsp/login.jsp";
		}
		
		Order order = new Order();
//		String name = request.getParameter("name");
//		String address = request.getParameter("address");
//		String telephone = request.getParameter("telephone");
		double total = 0;
		List<OrderItem> list = new ArrayList<OrderItem>();
		HashMap<Product, Integer>  map = (HashMap<Product, Integer>) session.getAttribute("map");
		
		for (Product product : map.keySet()) {
			OrderItem orderItem = new OrderItem();
			int count = map.get(product);
			double subtotal = count*product.getShop_price();
			
			orderItem.setItemid(UUIdUtils.getID());
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setCount(count);
			orderItem.setSubtotal(subtotal);
			total = total + subtotal;
			list.add(orderItem);
		}
		order.setOid(UUIdUtils.getID());
		order.setOrdertime(new Date());
		order.setState(0);
//		order.setName(name);
//		order.setAddress(address);
//		order.setTelephone(telephone);
		order.setTotal(total);
		order.setList(list);
		order.setUser(user);
		
		try {
			OrderService service = new OrderServiceImp();
			service.saveOrder(order);
			
			session.removeAttribute("map");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}

	public String confirmOrderByOid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user==null) {
			request.setAttribute("msg", "请登录后操作!");
			return "/jsp/login.jsp";
		}
		
		try {
			String oid = request.getParameter("oid");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");
			
			OrderService service = new OrderServiceImp();
			service.confirmOrderByOid(oid,name,address,telephone);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/jsp/confirm.jsp";
	}
	
	public String payOrderByOid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user==null) {
			request.setAttribute("msg", "请登录后操作!");
			return "/jsp/login.jsp";
		}
		
		try {
			String oid = request.getParameter("oid");
			OrderService service = new OrderServiceImp();
			Order order = service.payOrderByOid(user,oid);
			
			request.setAttribute("order", order);
			return "/jsp/order_info.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String showOrderByUid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user==null) {
			request.setAttribute("msg", "请登录后操作!");
			return "/jsp/login.jsp";
		}
		
		try {
			String pnum = request.getParameter("pnum");
			OrderService service = new OrderServiceImp();
			PageBean<Order> bean = service.showOrderByUid(user,pnum);
			
			request.setAttribute("bean", bean);
			return "/jsp/order_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	
	
}
