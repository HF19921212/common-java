package com.city.service;

import com.city.common.dto.EsProductDTO;
import com.city.common.service.EsProductService;
import com.city.dao.EsProductDao;
import com.city.document.EsProduct;
import com.city.repository.EsProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
        logger.info("导入ES数据{}:",iterator);
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        return count;
    }

    @Override
    public Page<EsProductDTO> searchPage(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        System.out.println("返回商品信息");
        logger.info("返回商品信息{}:",pageable);
        Page<EsProduct> page = esProductRepository.findByKeyword(keyword,keyword,keyword,pageable);
        Page<EsProductDTO> esProductDTOList = new PageImpl(page.stream().collect(Collectors.toList()),pageable,page.getTotalElements());
        return esProductDTOList;
    }
}
