package com.city.service;

import com.city.dao.EsProductDao;
import com.city.document.EsProduct;
import com.city.repository.EsProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * EsProductService接口的实现类
 * Created by zzb on 2019/12/4 11:06
 */
@Service
@Transactional
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
    public void delete(Long id) {
        logger.info("删除ES中的商品{}:",id);
        esProductRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        List<EsProduct> esProducts = esProductDao.getProductEs(id);
        if (CollectionUtils.isEmpty(esProducts)) {
            return null;
        }
        EsProduct esProduct = esProducts.get(0);
        logger.info("导入ES单条商品{}:",esProduct);
        return esProductRepository.save(esProduct);
    }

    @Override
    public void deletes(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            ids.forEach(id->{
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            });
            logger.info("批量删除ES中的商品{}:",esProductList);
            esProductRepository.deleteAll(esProductList);
        }
    }

    @Override
    public Page<EsProduct> searchPage(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        return esProductRepository.findByKeyword(keyword,keyword,keyword,pageable);
    }
}