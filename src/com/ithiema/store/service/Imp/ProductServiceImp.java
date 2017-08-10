package com.ithiema.store.service.Imp;

import java.sql.SQLException;
import java.util.List;
import com.ithiema.store.dao.ProductDAO;
import com.ithiema.store.dao.Imp.ProductDAOImp;
import com.ithiema.store.domain.PageBean;
import com.ithiema.store.domain.Product;
import com.ithiema.store.service.ProductService;

public class ProductServiceImp implements ProductService {

	@Override
	public PageBean<Product> findPageDataByCid(String cid, String pnum) throws SQLException {
		ProductDAO dao = new ProductDAOImp();
		PageBean<Product> pageBean = new PageBean<>();
		
		int pageSize = 12;
		long totalRows = dao.findProductTotalRows(cid);
		int currentPageNum = Integer.parseInt(pnum);
		int totalPageNum = (int) Math.ceil(totalRows*1.0f/pageSize);
		
		int offset = (currentPageNum-1)*pageSize;
		List<Product> datas = dao.findPageDataByCid(cid,offset,pageSize);
		
		pageBean.setTotalRows(totalRows);
		pageBean.setCurrentPageNum(currentPageNum);
		pageBean.setTotalPageNum(totalPageNum);
		pageBean.setDatas(datas);
		
		return pageBean;
	}

	@Override
	public List<Product> findLatestProduct() throws SQLException {
		ProductDAO dao = new ProductDAOImp();
		return dao.findLatestProduct();
	}

	@Override
	public List<Product> findHottestProduct() throws SQLException {
		ProductDAO dao = new ProductDAOImp();
		return dao.findHottestProduct();
	}

	@Override
	public Product findProductByPid(String pid) throws SQLException {
		ProductDAO dao = new ProductDAOImp();
		return dao.findProductByPid(pid);
	}

	@Override
	public void addProduct(Product product) throws SQLException {
		ProductDAO dao = new ProductDAOImp();
		dao.addProduct(product);
	}

	@Override
	public void editProduct(Product product) throws SQLException {
		ProductDAO dao = new ProductDAOImp();
		dao.editProduct(product);
	}

	@Override
	public void pushDownProductById(String pid) throws SQLException {
		ProductDAO dao = new ProductDAOImp();
		dao.pushDownProductById(pid);
	}

	@Override
	public List<Product> findpushDownProduct() throws SQLException {
		ProductDAO dao = new ProductDAOImp();
		return dao.findpushDownProduct();
	}

}
