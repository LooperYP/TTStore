package com.ithiema.store.dao.Imp;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ithiema.store.dao.UserDao;
import com.ithiema.store.domain.User;
import com.ithiema.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	@Override
	public void registUser(User user) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				user.getUid(),             //
				user.getUsername(),        //
				user.getPassword(),        //
				user.getName(),            //
				user.getEmail(),           //
				user.getBirthday(),        //
				user.getSex(),             //
				user.getState(),           //
				user.getCode()             //
		};
		qRunner.update(sql, params);
	}

	@Override
	public void activeUser(String code) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "update user set state=1 where code=?";
		qRunner.update(sql, code);
	}

	@Override
	public User loginUser(String username, String password) throws SQLException {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "select * from user where username=? and password=?";
		return qRunner.query(sql, new BeanHandler<User>(User.class), username, password);
	}

}
