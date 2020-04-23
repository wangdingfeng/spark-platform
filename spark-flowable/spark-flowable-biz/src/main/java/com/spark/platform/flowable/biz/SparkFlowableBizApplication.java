package com.spark.platform.flowable.biz;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableFeignClients(basePackages = "com.spark.platform.adminapi.feign.client")
@ComponentScan(basePackages = {"com.spark.platform"})
public class SparkFlowableBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkFlowableBizApplication.class, args);
    }

}
