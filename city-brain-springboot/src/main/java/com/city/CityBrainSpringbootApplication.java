package com.city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = "classpath:provider.xml")
@MapperScan("com.city.dao")
@SpringBootApplication
public class CityBrainSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityBrainSpringbootApplication.class, args);
    }

}
