package com.city.common.spi.wrapper;

import com.city.common.spi.IOrderService;
import org.apache.dubbo.common.URL;

public class OrderWrapper implements IOrderService {

    private IOrderService order;

    public OrderWrapper(IOrderService order) {
        this.order = order;
    }

    @Override
    public String way() {
        System.out.println("before-设置wrapper对pay()的增强");
        String way = order.way();
        System.out.println("after-设置wrapper对pay()的增强");
        return way;
    }

    @Override
    public String pay(URL url) {
        System.out.println("before-设置wrapper对pay()的增强");
        String pay = order.pay(url);
        System.out.println("after-设置wrapper对pay()的增强");
        return pay;
    }
}
