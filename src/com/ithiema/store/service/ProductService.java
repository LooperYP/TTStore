package com.ithiema.store.service;

import java.sql.SQLException;
import java.util.List;

import com.ithiema.store.domain.PageBean;
import com.ithiema.store.domain.Product;

public interface ProductService {

	PageBean<Product> findPageDataByCid(String cid, String pnum) throws SQLException;

	List<Product> findLatestProduct() throws SQLException;

	List<Product> findHottestProduct() throws SQLException;

	Product findProductByPid(String pid) throws SQLException;

	void addProduct(Product product) throws SQLException;

	void editProduct(Product product) throws SQLException;

	void pushDownProductById(String pid) throws SQLException;

	List<Product> findpushDownProduct() throws SQLException;

}
