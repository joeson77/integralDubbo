package com.test.until;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午7:04:00
 *
 * 功能描述： redis连接
 * 
 * 版本： V1.0
 */
public class RedisConnect {
	private static JedisPool jedisPool = null;

	public RedisConnect() {
	}

	// 获取连接
	public static synchronized Jedis getJedis() {
		if (jedisPool == null) {
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			// 指定连接池最大空闲连接数
			jedisPoolConfig.setMaxIdle(50);
			// 连接池中创建的最大连接数
			jedisPoolConfig.setMaxTotal(200);
			// 设置创建连接的超时时间
			jedisPoolConfig.setMaxWaitMillis(5000000);
			// 表示连接池在创建连接的时候会先测试一下连接是否可用，这样可以保证连接池中的连接都可以使用
			jedisPoolConfig.setTestOnBorrow(true);
			jedisPool = new JedisPool(jedisPoolConfig, "192.168.137.187", 6379);
		}
		return jedisPool.getResource();
	}

	// 返回连接
	public static void returnResources(Jedis jedis) {
		jedisPool.close();
	}
}
