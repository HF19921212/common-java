package com.city.common.spi;
import org.apache.dubbo.common.extension.ExtensionLoader;

public class IOrderService$Adaptive implements com.city.common.spi.IOrderService {
    public java.lang.String pay(org.apache.dubbo.common.URL arg0)  {
        if (arg0 == null) throw new IllegalArgumentException("url == null");
        org.apache.dubbo.common.URL url = arg0;
        String extName = url.getParameter("i.order.service", "alipay");
        if(extName == null) throw new IllegalStateException("Failed to get extension (com.city.common.spi.IOrderService) name from url (" + url.toString() + ") use keys([i.order.service])");
        com.city.common.spi.IOrderService extension = (com.city.common.spi.IOrderService)ExtensionLoader.getExtensionLoader(com.city.common.spi.IOrderService.class).getExtension(extName);
        return extension.pay(arg0);
    }
    public java.lang.String way()  {
        throw new UnsupportedOperationException("The method public abstract java.lang.String com.city.common.spi.IOrderService.way() of interface com.city.common.spi.IOrderService is not adaptive method!");
    }
}