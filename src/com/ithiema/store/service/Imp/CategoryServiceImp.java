package com.ithiema.store.service.Imp;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.ithiema.store.dao.CategoryDAO;
import com.ithiema.store.dao.Imp.CategoryDAOImp;
import com.ithiema.store.domain.Category;
import com.ithiema.store.service.CategoryService;
import com.ithiema.store.utils.JedisUtils;
import com.ithiema.store.utils.StringIsNull;

import redis.clients.jedis.Jedis;

public class CategoryServiceImp implements CategoryService {

	@Override
	public String findAllCategory() throws SQLException {
		CategoryDAO dao = new CategoryDAOImp();
		
		Jedis jedis = JedisUtils.getJedis();
		String json = jedis.get("findAllCategory");
		
		if (StringIsNull.isNull(json)) {
			List<Category> category = dao.findAllCategory();
			Gson gson = new Gson();
			json = gson.toJson(category);
			
			jedis.set("findAllCategory", json);
		}
		JedisUtils.closeJedis(jedis);
		return json;
	}

	@Override
	public void addCategory(String cid, String cname) throws Exception {
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("findAllCategory");
		
		CategoryDAO dao = new CategoryDAOImp();
		dao.addCategory(cid,cname);
		
		JedisUtils.closeJedis(jedis);
	}

	@Override
	public void delCategory(String cid) throws Exception {
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("findAllCategory");
		
		CategoryDAO dao = new CategoryDAOImp();
		dao.delCategory(cid);
		
		JedisUtils.closeJedis(jedis);
	}

	@Override
	public void editCategory(String cid, String cname) throws Exception {
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("findAllCategory");
		
		CategoryDAO dao = new CategoryDAOImp();
		dao.editCategory(cid,cname);
		
		JedisUtils.closeJedis(jedis);
	}

}
