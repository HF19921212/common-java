package com.city.repository;

import com.city.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 操作Elasticsearch的接口
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct,Long> {
    /**
     * 搜索查询
     * @param name
     * @param detailTitle
     * @param keyword
     * @param page
     * @return
     */
    Page<EsProduct> findByKeyword(String name, String detailTitle, String keyword, Pageable page);
}