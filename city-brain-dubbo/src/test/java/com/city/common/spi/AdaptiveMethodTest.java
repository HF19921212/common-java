package com.city.common.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

public class AdaptiveMethodTest {

    @Test
    public void test01(){
        ExtensionLoader<IOrderService> loader = ExtensionLoader.getExtensionLoader(IOrderService.class);
        //获取自适应扩展类
        IOrderService adaptiveExtension = loader.getAdaptiveExtension();
        //模拟一个URL
        URL url = URL.valueOf("xxxx://localhost/ooo");
        System.out.println(adaptiveExtension.pay(url));
    }

    @Test
    public void test02(){
        ExtensionLoader<IOrderService> loader = ExtensionLoader.getExtensionLoader(IOrderService.class);
        //获取自适应扩展类
        IOrderService adaptiveExtension = loader.getAdaptiveExtension();
        //模拟一个URL
        URL url = URL.valueOf("xxxx://localhost/ooo?i.order.service=weChat");
        System.out.println(adaptiveExtension.pay(url));
    }
    
}
