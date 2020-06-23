package com.city.common;

import com.city.common.crud.StringUtil;

public class StringTest {
    public static void main(String[] args) {
        String key ="KEY";
        String value = "VALUE";

        StringUtil stringUtil = new StringUtil();
        String str = stringUtil.setString(key,value);
        if(str.equals("OK")){
            System.out.println("StringUtil setString key:{"+key+" value:"+value+"}");
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

    }
}
