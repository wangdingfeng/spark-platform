package com.spark.platform.admin.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.vo
 * @ClassName: VueTree
 * @Author: wangdingfeng
 * @Description: vue 树节点数据
 * @Date: 2020/3/20 15:19
 * @Version: 1.0
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "VueTree",description = "VueTree树节点数据")
public class VueTree {

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "标签名")
    private String label;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "父ID")
    private Long pid;

    @ApiModelProperty(value = "子节点数据")
    private List<VueTree> children;
}
