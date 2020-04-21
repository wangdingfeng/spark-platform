package com.spark.platform.flowable.biz;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@ComponentScan(basePackages = {"com.spark.platform"})
public class SparkFlowableBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkFlowableBizApplication.class, args);
    }

}
