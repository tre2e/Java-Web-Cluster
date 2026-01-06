package com.example.ad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.example.ad.filter")
@MapperScan("com.example.ad.mapper")
@SpringBootApplication
public class AdApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdApplication.class, args);
    }
}