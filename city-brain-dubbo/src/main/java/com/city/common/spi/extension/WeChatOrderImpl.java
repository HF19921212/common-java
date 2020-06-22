package com.city.common.spi.extension;

import com.city.common.spi.IOrderService;
import org.apache.dubbo.common.URL;

public class WeChatOrderImpl implements IOrderService {
    @Override
    public String way() {
        System.out.println("---微信way()---");
        return "微信支付";
    }

    @Override
    public String pay(URL url) {
        System.out.println("---微信way()---");
        return "微信支付";
    }
}
