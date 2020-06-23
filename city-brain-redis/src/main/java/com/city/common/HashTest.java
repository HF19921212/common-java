package com.city.common;

import com.city.common.crud.HashUtil;

import java.util.HashMap;
import java.util.Map;

public class HashTest {
    public static void main(String[] args) {
        HashUtil hashUtil = new HashUtil();

        hashUtil.hset("1000","name","笔记本");
        hashUtil.hset("1001","name","空调");
        Map<String,String> map = new HashMap<String,String>();
        map.put("id","1002");
        map.put("goodName","钢笔");
        map.put("money","100");
        hashUtil.hmset("1002",map);
        System.out.println("插入一条记录 ："+hashUtil.hget("1000","name"));
        System.out.println("插入一条记录 ："+hashUtil.hget("1001","name"));
        System.out.println("插入一条记录 ："+hashUtil.hget("1002","goodName"));
        System.out.println("插入一条记录 ："+hashUtil.hget("1002","goodName"));
        //查看map
        System.out.println("插入一条记录 hmget："+hashUtil.hmget("1002","id","goodName","money"));

        hashUtil.hdel("1000","name");
        hashUtil.hdel("1001","name");
        //删除map
        hashUtil.hdel("1002","id","goodName","money");
    }
}
