package com.spark.platform.adminbiz.service.gen.impl;

import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.spark.platform.adminapi.entity.gen.TableColumnInfo;
import com.spark.platform.adminapi.vo.GeneratorConfigVo;
import com.spark.platform.adminbiz.dao.gen.GenDao;
import com.spark.platform.adminbiz.service.gen.GenService;
import com.spark.platform.common.config.datasource.DataSourceProperties;
import com.spark.platform.common.config.properties.SparkProperties;
import com.spark.platform.common.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.gen.impl
 * @ClassName: GenServiceImpl
 * @Author: wangdingfeng
 * @Description: 自动生成配置
 * @Date: 2020/4/15 11:55
 * @Version: 1.0
 */
@Service
@Slf4j
public class GenServiceImpl implements GenService {

    @Autowired
    private GenDao genDao;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private SparkProperties sparkProperties;

    @Override
    public IPage tableInfoPage(Page page, String tableName) {
        return genDao.tableInfoPage(page,dataSourceProperties.getName(), tableName);
    }

    @Override
    public List<TableColumnInfo> findTableColumnInfo(String tableName) {
        return genDao.findTableColumnInfo(dataSourceProperties.getName(), tableName);
    }

    @Override
    public String generatorCode(GeneratorConfigVo generatorConfigVo) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = sparkProperties.getFilePath() + File.separator + "codes" + File.separator + generatorConfigVo.getTableName();
        String outputDir = projectPath + "/src/main/java";
        gc.setOutputDir(outputDir);
        //覆盖文件
        gc.setFileOverride(true);
        gc.setAuthor(generatorConfigVo.getAuthor());
        gc.setOpen(true);
        //配置生成dao层和service路径
        gc.setMapperName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setEnableCache(false);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSourceProperties.getUrl());
        dsc.setDriverName(dataSourceProperties.getDriverClassName());
        dsc.setUsername(dataSourceProperties.getUsername());
        dsc.setPassword(dataSourceProperties.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(generatorConfigVo.getModelName());
        pc.setParent(generatorConfigVo.getParentPackage());
        mpg.setPackageInfo(pc);

        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        gc.setEnableCache(false);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.spark.platform.common.base.entity.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass("com.spark.platform.common.base.support.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("creator", "modifier", "create_date", "modify_date", "del_flag", "remarks");
        strategy.setInclude(generatorConfigVo.getTableName());
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        return projectPath;
    }

    @Override
    public void downLoadCodes(GeneratorConfigVo generatorConfigVo, HttpServletResponse response) {
        try {
            String zipFolderPath = generatorCode(generatorConfigVo);
            File zipFile = ZipUtil.zip(zipFolderPath);
            String fileName = generatorConfigVo.getTableName() + ".zip";
            FileUtil.download(fileName, new FileInputStream(zipFile), response);
        } catch (IOException e) {
            log.error("下载失败", e);
        }
    }
}
