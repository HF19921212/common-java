package com.city.config;

import com.city.interceptor.SSOFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean ssoFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(ssoFilterBean());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("ssoFilter");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Bean
    public Filter ssoFilterBean() {
        return new SSOFilter();
    }

}
