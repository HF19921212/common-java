package com.city.controller;

import com.city.common.dto.EsProductDTO;
import com.city.common.service.EsProductService;
import com.city.common.util.CommonPage;
import com.power.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    //搜索商品
    @RequestMapping(value = "/esProduct/search",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsProductDTO>> search(@RequestParam(required = false) String keyword,
                                                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize){
        CommonPage<EsProductDTO> esProductPage = esProductService.searchPage(keyword,pageNum,pageSize);
        return new CommonResult(true,"搜索商品", esProductPage);
    }

}
