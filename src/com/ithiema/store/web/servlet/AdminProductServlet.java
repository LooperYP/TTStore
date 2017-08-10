package com.ithiema.store.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import com.ithiema.store.domain.PageBean;
import com.ithiema.store.domain.Product;
import com.ithiema.store.service.ProductService;
import com.ithiema.store.service.Imp.ProductServiceImp;
import com.ithiema.store.utils.DateConverterUtils;
import com.ithiema.store.utils.UUIdUtils;
import com.ithiema.store.utils.UploadUtils;

public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findpushDownProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ProductService productService = new ProductServiceImp();
			List<Product> list = productService.findpushDownProduct();
			
			request.setAttribute("list", list);
			return "/admin/product/pushDown_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void pushDownProductById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pid = request.getParameter("pid");
			ProductService productService = new ProductServiceImp();
			productService.pushDownProductById(pid);
			
			response.sendRedirect(request.getContextPath()+"/adminProduct?action=findAllProduct&pnum=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory );
			
			List<FileItem> list = servletFileUpload.parseRequest(request);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					parameterMap.put(fieldName, value);
				} else {
					String fieldName = fileItem.getFieldName();
					
//					1. ie浏览器上name为带盘符的绝对路径
//					2. chrome和filefox浏览器上为文件名
					String name = fileItem.getName();
					String saveName = UploadUtils.getUUIDName(name);
					
					String savePath = "img"+UploadUtils.getDir(name);
					String dirPath = request.getServletContext().getRealPath(savePath);
					
					File dir = new File(dirPath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					
					File file = new File(dir, saveName);
					
					InputStream is = fileItem.getInputStream();
					FileOutputStream fos = new FileOutputStream(file);
					IOUtils.copy(is, fos);
					IOUtils.closeQuietly(fos);
					IOUtils.closeQuietly(is);
					
					parameterMap.put(fieldName, savePath+"/"+saveName);
				}
			}
			Product product = new Product();
			ConvertUtils.register(new DateConverterUtils(), Date.class);
			BeanUtils.populate(product, parameterMap);
			
			ProductService service = new ProductServiceImp();
			service.editProduct(product);
			
			response.sendRedirect(request.getContextPath()+"/adminProduct?action=findAllProduct&pnum=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String editSendPidProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pid = request.getParameter("pid");
			ProductService productService = new ProductServiceImp();
			Product bean = productService.findProductByPid(pid);
			
			request.setAttribute("bean", bean);
			return "/admin/product/edit.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory );
			
			List<FileItem> list = servletFileUpload.parseRequest(request);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					parameterMap.put(fieldName, value);
				} else {
					String fieldName = fileItem.getFieldName();
					
//					1. ie浏览器上name为带盘符的绝对路径
//					2. chrome和filefox浏览器上为文件名
					String name = fileItem.getName();
					String saveName = UploadUtils.getUUIDName(name);
					
					String savePath = "img"+UploadUtils.getDir(name);
					String dirPath = request.getServletContext().getRealPath(savePath);
					
					File dir = new File(dirPath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					
					File file = new File(dir, saveName);
					
					InputStream is = fileItem.getInputStream();
					FileOutputStream fos = new FileOutputStream(file);
					IOUtils.copy(is, fos);
					IOUtils.closeQuietly(fos);
					IOUtils.closeQuietly(is);
					
					parameterMap.put(fieldName, savePath+"/"+saveName);
				}
			}
			Product product = new Product();
			BeanUtils.populate(product, parameterMap);
			product.setPid(UUIdUtils.getID());
			product.setPdate(new Date());
			product.setPflag(0);
			
			ProductService service = new ProductServiceImp();
			service.addProduct(product);
			
			response.sendRedirect(request.getContextPath()+"/adminProduct?action=findAllProduct&pnum=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String findAllProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cid = request.getParameter("cid");
			String pnum = request.getParameter("pnum");
			
			ProductService productService = new ProductServiceImp();
			PageBean<Product> bean = productService.findPageDataByCid(cid,pnum);
			
			bean.setPageSize(12);
			request.setAttribute("pageBean", bean);
			return "/admin/product/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
