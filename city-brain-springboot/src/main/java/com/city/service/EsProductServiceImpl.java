package com.city.service;

import com.city.common.document.EsProduct;
import com.city.common.service.EsProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class EsProductServiceImpl implements EsProductService {
    @Override
    public Page<EsProduct> searchPage(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);

        System.out.println("返回商品信息");
        return null;
    }
}
