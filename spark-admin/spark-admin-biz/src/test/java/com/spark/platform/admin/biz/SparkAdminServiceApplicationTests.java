package com.spark.platform.admin.biz;

import com.spark.platform.admin.api.vo.GeneratorConfigVo;
import com.spark.platform.admin.biz.service.gen.GenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SparkAdminServiceApplicationTests {

    @Autowired
    GenService genService;

    @Test
    void contextLoads() {
        GeneratorConfigVo generatorConfigVo =new GeneratorConfigVo();
        generatorConfigVo.setTableName("sys_api_log");
        generatorConfigVo.setProjectName("spark-admin");
        generatorConfigVo.setAuthor("wangdingfeng");
        generatorConfigVo.setModelName("log");
        generatorConfigVo.setParentPackage("com.spark.platform.adminbiz");
        genService.generatorCode(generatorConfigVo);
    }

}
