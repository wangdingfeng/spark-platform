package com.spark.platform.admin.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SelectVo
 * @Author: wangdingfeng
 * @Date: 2020/12/14 11:35
 */
@Data
@ApiModel(value = "SelectVo",description = "SelectVo下拉框")
public class SelectVo {
    @ApiModelProperty(name = "标签值")
    private String value;
    @ApiModelProperty(name = "标签名")
    private String label;
}
