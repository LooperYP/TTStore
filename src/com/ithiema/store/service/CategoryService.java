package com.ithiema.store.service;

import java.sql.SQLException;
import java.util.List;
import com.ithiema.store.domain.Category;

public interface CategoryService {

	String findAllCategory() throws SQLException;

	void addCategory(String cid, String cname) throws Exception;

	void delCategory(String cid) throws Exception;

	void editCategory(String cid, String cname) throws Exception;
	
}
