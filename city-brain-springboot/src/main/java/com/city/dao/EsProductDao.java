package com.city.dao;

import com.city.elasticsearch.document.EsProduct;

import java.util.List;

/**
 * Elasticsearch商品搜索dao
 */
public interface EsProductDao {
    List<EsProduct> getProductEs(Long id);
}