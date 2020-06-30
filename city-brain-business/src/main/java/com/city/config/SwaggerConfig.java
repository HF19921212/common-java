package com.city.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author soldier
 * @Date 2020/3/11 11:42
 * @Version 1.0
 * @Description: Swagger配置
 */
@Configuration
public class SwaggerConfig {

    /**
     * 可以通过 apis()方法设置哪个包中内容被扫描
     * 通过http://localhost:8080/swagger-ui.html访问
     * @return
     */
    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerDemoApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.city.controller"))
                .build();
    }

    private ApiInfo swaggerDemoApiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("city-brain-business应用", "www.frend.com", "hf13216163956@aliyun.com"))
                // 标题
                .title("city-brain-business应用")
                // 描述
                .description("自己测试业务系统")
                // 版本
                .version("1.0.0")
                .build();
    }

}