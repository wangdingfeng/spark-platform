package com.spark.plateform.file.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/18 13:39
 * @Description: 绑定文件信息集合
 */
@Data
@ApiModel(value = "FileInfoDTO",description = "绑定文件信息集合")
public class FileInfoDTO {

    @ApiModelProperty(value = "文件ID集合")
    @NotEmpty(message = "文件ID集合 不能为空")
    private List<Long> fileIds;
    @ApiModelProperty(value = "删除文件ID")
    private List<Long> deleteFileIds;
    @ApiModelProperty(value = "业务ID")
    @NotNull(message = "业务ID 不能为空")
    private String bizId;
    @ApiModelProperty(value = "业务类型")
    @NotNull(message = "业务类型 不能为空")
    private String bizType;
    @ApiModelProperty(value = "请求服务名称(用来区分是那个服务器的文件)")
    @NotNull(message = "请求服务名称不能为空")
    private String serviceName;
}
