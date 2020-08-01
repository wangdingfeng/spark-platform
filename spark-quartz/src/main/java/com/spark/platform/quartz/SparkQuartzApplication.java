package com.spark.platform.quartz;

import com.spark.platform.common.security.annotation.EnableSparkResourceServer;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.spark.platform.quartz.**.dao"})
@EnableSparkResourceServer
@EnableSwagger2Doc
public class SparkQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkQuartzApplication.class, args);
    }

}
