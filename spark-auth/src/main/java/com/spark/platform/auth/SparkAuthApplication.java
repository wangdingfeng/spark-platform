package com.spark.platform.auth;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.spark.platform.adminapi.feign.client")
@ComponentScan(basePackages = {"com.spark.platform"})
@EnableSwagger2Doc
public class SparkAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkAuthApplication.class, args);
    }

}
