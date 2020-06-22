package com.city.common.spi;

import com.city.common.spi.adaptive.AdaptiveOrderImpl;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.Set;

public class AdaptiveClassTest {

    @Test
    public void test01(){
        ExtensionLoader<IOrderService> loader = ExtensionLoader.getExtensionLoader(IOrderService.class);
        //获取自适应扩展类
        IOrderService adativeExtension = loader.getAdaptiveExtension();
        ((AdaptiveOrderImpl)adativeExtension).setOrderName("weChat");
        System.out.println(adativeExtension.way());
    }

    @Test
    public void test02(){
        ExtensionLoader<IOrderService> loader = ExtensionLoader.getExtensionLoader(IOrderService.class);
        //获取自适应扩展类
        IOrderService adativeExtension = loader.getAdaptiveExtension();
        System.out.println(adativeExtension.way());
    }

    @Test
    public void test03(){
        ExtensionLoader<IOrderService> loader = ExtensionLoader.getExtensionLoader(IOrderService.class);
        //获取真正的扩展类
        Set<String> extensions = loader.getSupportedExtensions();
        System.out.println(extensions);
    }
}
