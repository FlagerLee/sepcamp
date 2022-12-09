package com.seclass.sepcamp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.seclass.sepcamp.daos") //扫描的mapper
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class SepcampApplication {

    public static void main(String[] args) {
        SpringApplication.run(SepcampApplication.class, args);
    }

}
