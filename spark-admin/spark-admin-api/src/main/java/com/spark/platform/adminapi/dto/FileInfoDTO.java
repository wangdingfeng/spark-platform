package com.spark.platform.adminapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/18 13:39
 * @Description: 绑定文件信息集合
 */
@Data
@ApiModel(value = "FileInfoDTO",description = "绑定文件信息集合")
public class FileInfoDTO {

    @ApiModelProperty(value = "文件ID")
    private List<Long> fileIds;

    @ApiModelProperty(value = "删除文件ID")
    private List<Long> deleteFileIds;

    @ApiModelProperty(value = "业务ID")
    private String bizId;

    @ApiModelProperty(value = "业务类型")
    private String bizType;
}
