package com.city.common.spi.extension;

import com.city.common.spi.IOrderService;

public class WeChatOrderImpl implements IOrderService {
    @Override
    public String way() {
        System.out.println("---微信way()---");
        return "微信支付";
    }
}
