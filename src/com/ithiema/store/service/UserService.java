package com.ithiema.store.service;

import java.sql.SQLException;

import com.ithiema.store.domain.User;

public interface UserService {

	void registUser(User user) throws SQLException;

	void activeUser(String code) throws SQLException;

	User loginUser(String username, String password) throws SQLException;
	
}
