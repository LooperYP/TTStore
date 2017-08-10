package com.ithiema.store.service.Imp;

import java.sql.SQLException;

import com.ithiema.store.dao.UserDao;
import com.ithiema.store.dao.Imp.UserDaoImp;
import com.ithiema.store.domain.User;
import com.ithiema.store.service.UserService;

public class UserServiceImp implements UserService {

	@Override
	public void registUser(User user) throws SQLException {
		UserDao dao = new UserDaoImp();
		dao.registUser(user);
	}

	@Override
	public void activeUser(String code) throws SQLException {
		UserDao dao = new UserDaoImp();
		dao.activeUser(code);
	}

	@Override
	public User loginUser(String username, String password) throws SQLException {
		UserDao dao = new UserDaoImp();
		return dao.loginUser(username,password);
	}
	
}
