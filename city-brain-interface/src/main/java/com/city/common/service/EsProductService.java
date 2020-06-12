package com.city.common.service;

import com.city.common.dto.EsProductDTO;
import com.city.common.util.CommonPage;

public interface EsProductService {

    /**
     * 从数据库中导入商品到ES
     * @return
     */
    int importAll();

    /**
     * 根据关键字搜索
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonPage<EsProductDTO> searchPage(String keyword, Integer pageNum, Integer pageSize);

}
