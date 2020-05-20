package com.city.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("hello")
    public String hello(){
        return "index";
    }
    
}
