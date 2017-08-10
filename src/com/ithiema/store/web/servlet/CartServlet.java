package com.ithiema.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ithiema.store.domain.Product;
import com.ithiema.store.service.ProductService;
import com.ithiema.store.service.Imp.ProductServiceImp;

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void changeQuantityCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pid = request.getParameter("pid");
			String quantity = request.getParameter("quantity");
			System.out.println(pid);
			System.out.println(quantity);
			int quantities = Integer.parseInt(quantity);
			
			ProductService service = new ProductServiceImp();
			Product product = service.findProductByPid(pid);

			HttpSession session = request.getSession();
			
			@SuppressWarnings("unchecked")
			HashMap<Product, Integer>  map = (HashMap<Product, Integer>) session.getAttribute("map");
			if (map==null) {
				map = new HashMap<Product, Integer>();
				map.put(product, quantities);
			}else {
				if (!map.containsKey(product)) {
					map.put(product, quantities);
				}else {
//					Integer num = map.get(product);
					map.put(product, quantities);
				}
			}
			session.setAttribute("map", map);
			response.getWriter().write("/jsp/cart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public String addCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pid = request.getParameter("pid");
			String quantity = request.getParameter("quantity");
			int quantities = Integer.parseInt(quantity);
			
			ProductService service = new ProductServiceImp();
			Product product = service.findProductByPid(pid);

			HttpSession session = request.getSession();
			
			@SuppressWarnings("unchecked")
			HashMap<Product, Integer>  map = (HashMap<Product, Integer>) session.getAttribute("map");
			if (map==null) {
				map = new HashMap<Product, Integer>();
				map.put(product, quantities);
			}else {
				if (!map.containsKey(product)) {
					map.put(product, quantities);
				}else {
					Integer num = map.get(product);
					map.put(product, num+quantities);
				}
			}
			session.setAttribute("map", map);
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String delProductCartByPid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		
		Product product = new Product();
		product.setPid(pid);
		
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<Product, Integer>  map = (HashMap<Product, Integer>) session.getAttribute("map");
		
		map.remove(product);
		session.setAttribute("map", map);
		
		return "/jsp/cart.jsp";
	}
	
	public String clearProductCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("map");
		
		return "/jsp/cart.jsp";
	}
	
	
}