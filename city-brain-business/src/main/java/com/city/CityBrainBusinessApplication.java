package com.city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@PropertySource(value = "classpath:resource.properties")
@EnableSwagger2
@MapperScan(basePackages = "com.city.repository")
@SpringBootApplication
public class CityBrainBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityBrainBusinessApplication.class, args);
    }

}
