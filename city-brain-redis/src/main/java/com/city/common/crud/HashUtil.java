package com.city.common.crud;

import com.city.common.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

public class HashUtil {

    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();

    /**
     * 插入一条记录
     * @param key 唯一
     * @param field 可重复
     * @param value 可重复
     * @return
     */
    public boolean hset( String key, String field, String value){
        Jedis jedis = jedisPool.getResource();
        Long l = jedis.hset(key,field,value);
        if(l>0){
            return true;
        }
        return false;
    }

    /**
     * 插入一个map集合
     * @param key
     * @param map
     * @return
     */
    public boolean hmset(String key, Map map){
        Jedis jedis = jedisPool.getResource();
        String str = jedis.hmset(key,map);
        if("OK".equals(str)){
            return true;
        }
        return false;
    }


    /**
     * 查看
     * @param key
     * @param field
     * @return
     */
    public String hget( String key, String field){
        Jedis jedis = jedisPool.getResource();
        String str = jedis.hget(key,field);
        if(null != str){
            return str;
        }
        return null;
    }

    /**
     * 查看哈希集合
     * @param key
     * @param field
     * @return
     */
    public List<String> hmget(String key, String... field){
        Jedis jedis = jedisPool.getResource();
        List<String> list = jedis.hmget(key,field);
        if(null != list){
            return list;
        }
        return null;
    }

    /**
     * 删除
     * @param key
     * @param field
     * @return
     */
    public boolean hdel( String key, String... field){
        Jedis jedis = jedisPool.getResource();
        Long l = jedis.hdel(key,field);
        if(l>0){
            return true;
        }
        return false;
    }

}
