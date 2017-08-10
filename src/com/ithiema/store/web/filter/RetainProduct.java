package com.ithiema.store.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ithiema.store.domain.Product;
import com.ithiema.store.service.ProductService;
import com.ithiema.store.service.Imp.ProductServiceImp;
import com.ithiema.store.utils.CookieUtils;

public class RetainProduct implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest r, ServletResponse p, FilterChain chain) throws IOException, ServletException {
//		1.0强制类型转换
		HttpServletRequest request = (HttpServletRequest) r;
		HttpServletResponse response = (HttpServletResponse) p;
		
		try {
			boolean isExist = CookieUtils.cookieIsExist(request, "retainPid");
			if (isExist) {
				ProductService service = new ProductServiceImp();
				List<Product> retainList = new ArrayList<Product>();
				
				Cookie retainPid = CookieUtils.getCookie(request, "retainPid");
				String value = retainPid.getValue();
				String[] split = value.split("-");
				for (int i = 0; i < split.length; i++) {
					Product product = service.findProductByPid(split[i]);
					retainList.add(product);
				}
				request.setAttribute("retainList", retainList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
