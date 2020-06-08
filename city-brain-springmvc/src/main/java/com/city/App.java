package com.city;

import com.city.common.service.EsProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");

        EsProductService esProductService = (EsProductService) context.getBean("esProductService");

        esProductService.searchPage("",1,10);
    }
}
