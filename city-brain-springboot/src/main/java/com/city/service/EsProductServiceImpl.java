package com.city.service;

import com.city.common.dto.EsProductDTO;
import com.city.common.bio.service.EsProductService;
import com.city.common.util.CommonPage;
import com.city.dao.EsProductDao;
import com.city.elasticsearch.document.EsProduct;
import com.city.elasticsearch.repository.EsProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EsProductServiceImpl implements EsProductService {

    private static final Logger logger = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private EsProductDao esProductDao;
    @Autowired
    private EsProductRepository esProductRepository;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = esProductDao.getProductEs(null);
        Iterable<EsProduct> iterable = esProductRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = iterable.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        logger.info("导入ES数据{}:",count+"条");
        return count;
    }

    @Override
    public CommonPage<EsProductDTO> searchPage(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<EsProduct> page = esProductRepository.findByKeyword(keyword,keyword,keyword,pageable);
        logger.info("返回商品信息{}:",page);
        List<EsProduct> esProductList = page.getContent();
        List<EsProductDTO> esProductDTOList = new ArrayList<>();
        BeanUtils.copyProperties(esProductList,esProductDTOList);
        Page<EsProductDTO> productList = new PageImpl(esProductDTOList,pageable,page.getTotalElements());
        return new CommonPage(productList);
    }
}
