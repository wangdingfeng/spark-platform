package com.spark.plateform.file.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.plateform.file.api.dto
 * @ClassName: FileQueryDTO
 * @Author: wangdingfeng
 * @Description: 文件查询DTO
 * @Date: 2020/11/6 13:50
 * @Version: 1.0
 */
@Data
@ApiModel(value = "FileQueryDTO",description = "文件信息查询")
public class FileQueryDTO {
    @ApiModelProperty(value = "业务ID")
    private String bizId;
    @ApiModelProperty(value = "业务类型")
    private String bizType;
    @ApiModelProperty(value = "服务名称")
    private String serviceName;
}
