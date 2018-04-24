package com.listen.sql.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Redis {
    private static Redis instance;
    public static Logger logger = LoggerFactory.getLogger(Redis.class);
    public static final int DB = 0;//默认数据库
    public static String password = null;
    JedisPool pool = null;

    public static Redis getInstance() {
        if (instance == null) {
            instance = new Redis();
            instance.init();
        }
        return instance;
    }

    private void init() {
        String redisServer = "127.0.0.1:6379";
        redisServer = redisServer.trim();
        String[] temp = redisServer.split(":");
        String host = temp[0];
        int port = Integer.parseInt(temp[1]);
        if (temp.length == 2) {
            port = Integer.parseInt(temp[1].trim());
        }

        logger.info("Redis at {}:{} ", host, port);

        /**
         * redis 使用
         */
        pool = new JedisPool(host, port);

        /**
         * redis 集群管理
         */
//        Set sentinels = new HashSet();
//        sentinels.add(new HostAndPort(host, port).toString());
//        pool = new JedisPool("master1", sentinels.size());
    }

    public void select(int index){
        Jedis j = pool.getResource();
        String select = j.select(index);
        j.close();
    }
    public Long hdel(int db ,String key ,String... fields){
        Jedis redis = this.pool.getResource();
        redis.select(db);
        Long cnt = redis.hdel(key,fields);
        redis.close();
        return cnt;
    }

    public Map<String,String> hgetAll(int db, String key){
        if (key == null){
            return null;
        }
        Jedis redis = this.pool.getResource();
        redis.select(db);
        Map<String,String> ret = redis.hgetAll(key);
        redis.close();
        return ret;
    }

    public String hget(int db,String key, String field){
        if (key == null){
            return null;
        }
        Jedis redis = this.pool.getResource();
        redis.select(db);
        String ret = redis.hget(key,field);
        redis.close();
        return  ret;
    }

    public void hset(int db, String key, String field, String value){
        if (field == null || field.length() == 0){
            return;
        }

        if (value == null || value.length() == 0){
            return;
        }

        Jedis redis = this.pool.getResource();
        redis.select(db);
        redis.hset(key, field, value);
        redis.close();
    }

    public void set(int db, String key, String value){
        if (value == null || key == null){
            return;
        }
        Jedis redis = this.pool.getResource();
        redis.select(db);
        redis.set(key, value);
        redis.close();
    }

    public String get(int db, String key){
        Jedis redis = this.pool.getResource();
        redis.select(db);
        String ret = redis.get(key);
        redis.close();
        return ret;
    }

    /**
     * 添加元素到集合中
     */

}
