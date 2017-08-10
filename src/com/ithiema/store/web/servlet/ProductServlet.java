package com.ithiema.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ithiema.store.domain.PageBean;
import com.ithiema.store.domain.Product;
import com.ithiema.store.service.ProductService;
import com.ithiema.store.service.Imp.ProductServiceImp;
import com.ithiema.store.utils.CookieUtils;

public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findProductByPid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pid = request.getParameter("pid");
			ProductService service = new ProductServiceImp();
			Product product = service.findProductByPid(pid);

			request.setAttribute("result", product);

			boolean isExist = CookieUtils.cookieIsExist(request, "retainPid");
			if (isExist) {
				Cookie retainPid = CookieUtils.getCookie(request, "retainPid");
				String value = retainPid.getValue();
				String[] split = value.split("-");
				value = "";
				for (int i = 0; i < split.length; i++) {
					if (split[i].equals(pid)) {
						isExist = false;
					}
				}
				if (isExist) {
					if (split.length >= 5) {
						for (int i = 1; i < split.length; i++) {
							value += split[i] + "-";
						}
						value += pid + "-";
					} else {
						for (int i = 0; i < split.length; i++) {
							value += split[i] + "-";
						}
						value += pid + "-";
					}
					retainPid.setValue(value);
					retainPid.setPath("/");
					retainPid.setMaxAge(60 * 60 * 24);
					response.addCookie(retainPid);
				}
			} else {
				Cookie cookie = new Cookie("retainPid", pid);
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie);
			}

			return "/jsp/product_info.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String findIndexProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ProductService productService = new ProductServiceImp();
			List<Product> latest = productService.findLatestProduct();
			List<Product> hottest = productService.findHottestProduct();

			request.setAttribute("latest", latest);
			request.setAttribute("hottest", hottest);

			return "/jsp/index.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String findPageDataByCid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cid = request.getParameter("cid");
			String pnum = request.getParameter("pnum");

			ProductService productService = new ProductServiceImp();

			PageBean<Product> bean = productService.findPageDataByCid(cid, pnum);

			request.setAttribute("result", bean);

			return "/jsp/product_list.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
