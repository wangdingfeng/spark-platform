package com.spark.platform.adminapi.vo;

import lombok.Data;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.vo
 * @ClassName: GeneratorConfigVo
 * @Author: wangdingfeng
 * @Description: 代码生成配置
 * @Date: 2020/4/15 13:21
 * @Version: 1.0
 */
@Data
public class GeneratorConfigVo {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 作者
     */
    private String author;
    /**
     * 项目名
     */
    private String projectName;
    /**
     * 模块名
     */
    private String modelName;
    /**
     * 父类包名
     */
    private String parentPackage;
}
