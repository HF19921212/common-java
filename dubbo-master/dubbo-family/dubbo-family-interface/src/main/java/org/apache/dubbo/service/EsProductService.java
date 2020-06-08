package org.apache.dubbo.service;

import org.apache.dubbo.dto.EsProductDTO;
import org.springframework.data.domain.Page;

public interface EsProductService {

    /**
     * 根据关键字搜索
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsProductDTO> searchPage(String keyword, Integer pageNum, Integer pageSize);

}
