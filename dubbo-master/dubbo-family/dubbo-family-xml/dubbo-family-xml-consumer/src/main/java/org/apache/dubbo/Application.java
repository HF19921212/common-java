package org.apache.dubbo;

import org.apache.dubbo.service.EsProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");

        EsProductService esProductService = (EsProductService) context.getBean("esProductService");

        esProductService.searchPage("",1,10);
    }
}
