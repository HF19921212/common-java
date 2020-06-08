package org.apache.dubbo.service;

import org.apache.dubbo.dto.EsProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class EsProductServiceImpl implements EsProductService {
    @Override
    public Page<EsProductDTO> searchPage(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        System.out.println("返回商品信息");
        return null;
    }
}
