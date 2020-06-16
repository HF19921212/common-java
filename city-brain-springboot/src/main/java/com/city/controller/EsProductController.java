package com.city.controller;

import com.city.common.bio.service.EsProductService;
import com.power.common.model.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    @RequestMapping(value = "/esProduct/importAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> importAll(){
        int count = esProductService.importAll();
        if(count > 0){
            return CommonResult.ok();
        }
        return CommonResult.fail();
    }

}
