package com.city.common.service;

import com.city.common.dto.EsProductDTO;
import org.springframework.data.domain.Page;

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
    Page<EsProductDTO> searchPage(String keyword, Integer pageNum, Integer pageSize);

}
