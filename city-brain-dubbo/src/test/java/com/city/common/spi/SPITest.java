package com.city.common.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

public class SPITest {

    @Test
    public void test1(){
        ExtensionLoader<IOrderService> loader = ExtensionLoader.getExtensionLoader(IOrderService.class);
        //加载
        IOrderService alipay = loader.getExtension("alipay");
        System.out.println(alipay.way());

        IOrderService weChat = loader.getExtension("weChat");
        System.out.println(weChat.way());

    }
}
