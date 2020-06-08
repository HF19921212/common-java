package com.city;

import com.alibaba.dubbo.container.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityBrainSpringbootApplication {

    public static void main(String[] args) {
        //SpringApplication.run(CityBrainSpringbootApplication.class, args);
        Main.main(args);
    }

}
