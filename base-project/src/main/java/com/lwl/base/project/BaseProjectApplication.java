package com.lwl.base.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LinWenLi
 */
@MapperScan(basePackages = "com.lwl.base.project.dao")
@SpringBootApplication
public class BaseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseProjectApplication.class, args);
    }
}
