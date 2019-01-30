package com.foxboro.tools;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisAPI {
	public JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	
	public static void returnResource(JedisPool pool,Jedis jedis){
		if(jedis!=null){
			pool.returnResource(jedis);
		}
	}
	
	/**
	 * set key and value to redis
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key,String value){
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			jedis.set(key, value);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			returnResource(jedisPool,jedis);
		}
		return false;
	}
	
	//判断某个key是否存在
	public boolean exist(String key){
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			returnResource(jedisPool,jedis);
		}
		return false;
	}
	
	/**
	 * 获取数据
	 * @param key
	 * @return
	 */
	public String get(String key){
		String value=null;
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			value=jedis.get(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			returnResource(jedisPool,jedis);//返还到连接池
		}
		return value;
	}
	
	/**
	 * del key and value to redis
	 * @param keys
	 * @return
	 */
	public boolean del(String keys){
		Jedis jedis=null;
		try {
			jedis=jedisPool.getResource();
			jedis.del(keys);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			returnResource(jedisPool,jedis);
		}
		return false;
	}
	
	
}
