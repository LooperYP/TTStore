package com.ithiema.store.dao.Imp;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.ithiema.store.dao.CategoryDAO;
import com.ithiema.store.domain.Category;
import com.ithiema.store.utils.JDBCUtils;

public class CategoryDAOImp implements CategoryDAO {

	@Override
	public List<Category> findAllCategory() throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "select * from category";
		return qRunner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	@Override
	public void addCategory(String cid, String cname) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "insert into category values(?,?)";
		qRunner.update(sql,cid,cname);
	}

	@Override
	public void delCategory(String cid) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "delete from category where cid=?";
		qRunner.update(sql,cid);
	}

	@Override
	public void editCategory(String cid, String cname) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "update category set cname=? where cid=?";
		qRunner.update(sql,cname,cid);
	}

	
}
