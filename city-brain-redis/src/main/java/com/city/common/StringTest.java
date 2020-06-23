package com.city.common;

import com.city.common.crud.StringUtil;
import com.city.common.model.User;
import com.city.common.util.SerializeUtil;

public class StringTest {
    public static void main(String[] args) {
        String key ="KEY";
        String value = "VALUE";

        StringUtil stringUtil = new StringUtil();
        String str = stringUtil.setString(key,value);
        if(str.equals("OK")){
            System.out.println("StringUtil setString key:{"+key+" value:"+value+"}");
        }

        User user = new User();
        user.setId(1000);
        user.setName("苹果笔记本");
        user.setAge(27);
        String u = stringUtil.set(("user:"+user.getId()).getBytes(), SerializeUtil.serialize(user));
        if(u.equals("OK")){
            byte[] byteUser = stringUtil.getKey(("user:1000").getBytes());
            User user1 = (User)SerializeUtil.unserialize(byteUser);
            System.out.println("StringUtil set byte key:{"+user.getId()+"}");
            System.out.println("StringUtil set byte value:{"+user1+"}");
        }

        System.out.println("StringUtil updateString key:{"+key+"} , value:{"+stringUtil.updateString(key,"a")+"}");

        System.out.println("StringUtil getKey key:{"+stringUtil.getKey(key)+"}");

        long nx = stringUtil.setNx(key,value);
        if(nx == 1){
            System.out.println("StringUtil setNx key:{"+key+" value:"+value+"}");
        }

        boolean f = stringUtil.deleteString(key);
        if(f){
            System.out.println("StringUtil deleteString key：{"+key+"}");
        }

        //删除序列化对象
        stringUtil.deleteString("user:1000");

    }
}
