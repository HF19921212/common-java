package com.city.common.spi.adaptive;

import com.city.common.spi.IOrderService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.util.StringUtils;

@Adaptive
public class AdaptiveOrderImpl implements IOrderService {

    //扩展类名
    private  String orderName;

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String way() {
        //获取IOrderService实例
        ExtensionLoader<IOrderService> loader = ExtensionLoader.getExtensionLoader(IOrderService.class);
        IOrderService order;
        if(StringUtils.isEmpty(orderName)){
            //获取默认的实例
            order = loader.getDefaultExtension();
        }else{
            //获取指定实例
            order = loader.getExtension(orderName);
        }
        return order.way();
    }

    @Override
    public String pay(URL url) {
        return null;
    }
}
