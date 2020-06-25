package com.brain.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.brain.ums.mapper")
@SpringBootApplication
public class CityBrainUmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityBrainUmsApplication.class, args);
    }

}
