package com.spark.platform.auth;

import com.spark.platform.common.security.annotation.EnableSparkResourceServer;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSparkResourceServer
@EnableSwagger2Doc
public class SparkAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkAuthApplication.class, args);
    }

}
