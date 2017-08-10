package com.ithiema.store.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	//创建连接池
	private static JedisPoolConfig config;//配置连接池信息
	private static JedisPool pool;//连接池
	
	static{
		config=new JedisPoolConfig();//配置连接池信息
		config.setMaxTotal(30); //最大连接数
		config.setMaxIdle(2);   //最大空闲连接数
		
		pool=new JedisPool(config, "localhost", 6379); //创建连接池对象
	}
	
	
	//获取连接的方法
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	
	//释放连接
	public static void closeJedis(Jedis j){
		j.close();
	}
}
