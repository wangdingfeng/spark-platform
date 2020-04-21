package com.spark.platform.control;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAdminServer
@SpringBootApplication
public class SparkControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkControlApplication.class, args);
    }

}
