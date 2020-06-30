package com.city.config;

import com.city.interceptor.SSOFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean ssoFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new SSOFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("ssoFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}
