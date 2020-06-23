package com.city.common.crud;

import com.city.common.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class StringUtil {

    JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();

    /**
     * 设置指定字符串
     * @param key
     * @param value
     * @return OK
     */
    public String setString(String key,String value){
        Jedis jedis = jedisPool.getResource();
        String str = jedis.set(key,value);
        return str;
    }

    /**
     * 设置指定字符串
     * @param key
     * @param value
     * @return 1
     */
    public long setNx(String key,String value){
        Jedis jedis = jedisPool.getResource();
        long l = jedis.setnx(key,value);
        return l;
    }

    /**
     * 设置序列化对象
     * 与 byte[] getKey(byte[] key) 匹配
     * @param key
     * @param value
     * @return
     */
    public String set(byte[] key, byte[] value){
        Jedis jedis = jedisPool.getResource();
        String str = jedis.set(key,value);
        return str;
    }

    /**
     * 更新
     * @param key
     * @param value
     * @return
     */
    public String updateString(String key,String value){
        Jedis jedis = jedisPool.getResource();
        String str = jedis.set(key,value);
        return str;
    }

    /**
     * 查看
     * @param key
     * @return
     */
    public String getKey(String key){
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get(key);
        return str;
    }

    /**
     * 查看
     * 与 String set(byte[] key, byte[] value) 匹配
     * @param key
     * @return
     */
    public byte[] getKey(byte[] key){
        Jedis jedis = jedisPool.getResource();
        byte[] str = jedis.get(key);
        return str;
    }

    /**
     * 删除指定key
     * @param key
     * @return
     */
    public boolean deleteString(String key){
        Jedis jedis = jedisPool.getResource();
        Long l = jedis.del(key);
        if(l>0){
            return true;
        }
        return false;
    }

}
