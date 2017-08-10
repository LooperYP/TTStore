package com.ithiema.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.ithiema.store.domain.Product;

public interface ProductDAO {

	long findProductTotalRows(String cid) throws SQLException;

	List<Product> findPageDataByCid(String cid, int offset, int pageSize) throws SQLException;

	List<Product> findLatestProduct() throws SQLException;

	List<Product> findHottestProduct() throws SQLException;

	Product findProductByPid(String pid) throws SQLException;

	void addProduct(Product product) throws SQLException;

	void editProduct(Product product) throws SQLException;

	void pushDownProductById(String pid) throws SQLException;

	List<Product> findpushDownProduct() throws SQLException;

}
