package com.spark.platform.adminbiz;

import com.spark.platform.common.security.annotation.EnableSparkResourceServer;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.spark.platform.adminbiz.dao"})
@EnableSparkResourceServer
@EnableSwagger2Doc
public class SparkAdminBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkAdminBizApplication.class, args);
    }

}
