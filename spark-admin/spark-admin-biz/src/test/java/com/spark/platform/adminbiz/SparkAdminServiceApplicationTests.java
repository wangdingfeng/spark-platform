package com.spark.platform.adminbiz;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.spark.platform.adminapi.dto.FileInfoDTO;
import com.spark.platform.adminapi.entity.file.FileInfo;
import com.spark.platform.adminapi.vo.GeneratorConfigVo;
import com.spark.platform.adminbiz.service.file.FileInfoService;
import com.spark.platform.adminbiz.service.gen.GenService;
import com.spark.platform.common.base.support.WrapperSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SparkAdminServiceApplicationTests {

    @Autowired
    GenService genService;
    @Autowired
    FileInfoService fileInfoService;


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

    @Test
    void bind() {
        FileInfoDTO fileInfoDTO = new FileInfoDTO();
        fileInfoDTO.setBizId("10002");
        fileInfoDTO.setBizType("PROCESS_ARTICLE");
        fileInfoDTO.setFileIds(Lists.newArrayList(2l));
        fileInfoService.bindFile(fileInfoDTO);
    }

    @Test
    void wrapperTest(){
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setStatus("1");
        fileInfo.setFileName("我的");
        WrapperSupport.putParamsEqual(queryWrapper,"",fileInfo,"status");
        WrapperSupport.putParamsLike(queryWrapper,"",fileInfo,"fileName");
        List<FileInfo> fileInfoList = fileInfoService.list(queryWrapper);
        System.out.printf(""+fileInfoList.size());
    }

}
