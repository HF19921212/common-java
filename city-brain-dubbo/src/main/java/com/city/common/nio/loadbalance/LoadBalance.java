package com.city.common.nio.loadbalance;

import java.util.List;

public interface LoadBalance {

    public String select(List<String> repos);
}
