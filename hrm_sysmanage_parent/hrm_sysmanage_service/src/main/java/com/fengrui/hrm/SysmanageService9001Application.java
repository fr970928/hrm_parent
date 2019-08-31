package com.fengrui.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.fengrui.hrm.mapper")
public class SysmanageService9001Application {
    public static void main(String[] args) {
        SpringApplication.run(SysmanageService9001Application.class, args);
    }
}
