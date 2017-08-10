package com.ithiema.store.dao;

import java.sql.SQLException;
import java.util.List;
import com.ithiema.store.domain.Category;

public interface CategoryDAO {

	List<Category> findAllCategory() throws SQLException;

	void addCategory(String cid, String cname) throws SQLException;

	void delCategory(String cid) throws SQLException;

	void editCategory(String cid, String cname) throws SQLException;

}
