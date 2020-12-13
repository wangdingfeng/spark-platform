package com.spark.platform.wx.shop.biz;

import com.spark.platform.common.security.annotation.EnableSparkResourceServer;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"com.spark.platform.wx.shop.biz.**.dao"})
@EnableSparkResourceServer
@EnableSwagger2Doc
public class SparkWxShopBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkWxShopBizApplication.class, args);
    }

}
