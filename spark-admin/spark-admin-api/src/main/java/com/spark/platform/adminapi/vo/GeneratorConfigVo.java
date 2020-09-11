package com.spark.platform.adminapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "GeneratorConfigVo",description = "代码生成配置")
public class GeneratorConfigVo {

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "项目名")
    private String projectName;

    @ApiModelProperty(value = "模块名")
    private String modelName;

    @ApiModelProperty(value = "父类包名")
    private String parentPackage;
}
