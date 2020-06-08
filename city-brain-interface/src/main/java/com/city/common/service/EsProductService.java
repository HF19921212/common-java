package com.city.common.service;

import com.city.common.document.EsProduct;
import org.springframework.data.domain.Page;

public interface EsProductService {

    /**
     * 根据关键字搜索
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsProduct> searchPage(String keyword, Integer pageNum, Integer pageSize);

}
