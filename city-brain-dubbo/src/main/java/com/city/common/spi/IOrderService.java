package com.city.common.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI("alipay")
public interface IOrderService {
    //支付方式
    String way();

    @Adaptive
    String pay(URL url);
}
