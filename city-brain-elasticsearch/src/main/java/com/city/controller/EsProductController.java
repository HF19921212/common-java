package com.city.controller;

import com.city.common.CommonPage;
import com.city.elasticsearch.document.EsProduct;
import com.city.service.EsProductService;
import com.power.common.enums.HttpCodeEnum;
import com.power.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ES搜索商品Controller
 */
@Controller
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    //从数据库导入ES商品数据
    @RequestMapping(value = "/esProduct/importAll",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult importAll(){
        int count = esProductService.importAll();
        return CommonResult.ok(HttpCodeEnum.SUCCESS);
    }

    //根据id删除商品
    @RequestMapping(value = "/esProduct/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteById(@PathVariable Long id){
        esProductService.delete(id);
        return CommonResult.ok(HttpCodeEnum.SUCCESS);
    }

    //批量删除商品
    @RequestMapping(value = "/esProduct/deletes",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteById(List<Long> ids){
        esProductService.deletes(ids);
        return CommonResult.ok(HttpCodeEnum.SUCCESS);
    }

    //根据id创建商品
    @RequestMapping(value = "/esProduct/create",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(Long id){
        EsProduct esProduct = esProductService.create(id);
        if (StringUtils.isEmpty(esProduct)) {
            return CommonResult.fail();
        }
        return CommonResult.ok(HttpCodeEnum.SUCCESS);
    }

    //搜索商品
    @RequestMapping(value = "/esProduct/search",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        Page<EsProduct> esProductPage = esProductService.searchPage(keyword,pageNum,pageSize);
        return new CommonResult(true,"搜索商品", CommonPage.restPage(esProductPage));
    }
}