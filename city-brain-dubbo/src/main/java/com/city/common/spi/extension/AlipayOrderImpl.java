package com.city.common.spi.extension;

import com.city.common.spi.IOrderService;

public class AlipayOrderImpl implements IOrderService {
    @Override
    public String way() {
        System.out.println("---支付宝way()---");
        return "支付宝支付";
    }
}
