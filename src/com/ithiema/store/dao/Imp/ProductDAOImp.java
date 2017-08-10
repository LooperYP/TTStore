package com.ithiema.store.dao.Imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ithiema.store.dao.ProductDAO;
import com.ithiema.store.domain.Product;
import com.ithiema.store.utils.JDBCUtils;
import com.ithiema.store.utils.StringIsNull;

public class ProductDAOImp implements ProductDAO {

	@Override
	public long findProductTotalRows(String cid) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		if (StringIsNull.isNull(cid)) {
			String sql = "select count(1) from product";
			return (long) qRunner.query(sql , new ScalarHandler());
		}else {
			String sql = "select count(1) from product where cid=?";
			return (long) qRunner.query(sql , new ScalarHandler(),cid);
		}
	}

	@Override
	public List<Product> findPageDataByCid(String cid, int offset, int pageSize) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		ArrayList<Object> params = new ArrayList<Object>();
		String addcid = "";
		if (!StringIsNull.isNull(cid)) {
			params.add(cid);
			addcid = " where cid=? ";
		}
		params.add(offset);
		params.add(pageSize);
		String sql = "select * from product "+addcid+" limit ?,?";
		return qRunner.query(sql , new BeanListHandler<Product>(Product.class),params.toArray());
	}

	@Override
	public List<Product> findLatestProduct() throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "SELECT * FROM product ORDER BY pdate DESC LIMIT 0,9";
		return qRunner.query(sql , new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findHottestProduct() throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "SELECT * FROM product where is_hot=1 LIMIT 0,9";
		return qRunner.query(sql , new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "select * from product where pid=?";
		return qRunner.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	@Override
	public void addProduct(Product product) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				product.getPid(),                   //
				product.getPname(),                 //
				product.getMarket_price(),          //
				product.getShop_price(),            //
				product.getPimage(),                //
				product.getPdate(),                 //
				product.getIs_hot(),                //
				product.getPdesc(),                 //
				product.getPflag(),                 //
				product.getCid()                    //
		};
		qRunner.update(sql, params);
	}

	@Override
	public void editProduct(Product product) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "update product set pname=?,market_price=?,shop_price=?,"
				+ "pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
		Object[] params = {
				product.getPname(),                 //
				product.getMarket_price(),          //
				product.getShop_price(),            //
				product.getPimage(),                //
				product.getPdate(),                 //
				product.getIs_hot(),                //
				product.getPdesc(),                 //
				product.getPflag(),                 //
				product.getCid(),                   //
				product.getPid()                    //
		};
		qRunner.update(sql, params);
	}

	@Override
	public void pushDownProductById(String pid) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "update product set pflag=1 where pid=?";
		qRunner.update(sql, pid);
	}

	@Override
	public List<Product> findpushDownProduct() throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "select * from product where pflag=1";
		return qRunner.query(sql, new BeanListHandler<Product>(Product.class));
	}
	
}
