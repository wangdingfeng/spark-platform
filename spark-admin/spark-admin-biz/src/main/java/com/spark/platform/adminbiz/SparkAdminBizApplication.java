package com.spark.platform.adminbiz;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.spark.platform.adminbiz.dao"})
@EnableFeignClients(basePackages = "com.spark.platform.adminapi.feign.client")
@ComponentScan(basePackages = {"com.spark.platform"})
@EnableSwagger2Doc
public class SparkAdminBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkAdminBizApplication.class, args);
    }

}
