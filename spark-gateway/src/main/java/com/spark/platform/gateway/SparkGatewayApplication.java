package com.spark.platform.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SparkGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkGatewayApplication.class, args);
    }

}
