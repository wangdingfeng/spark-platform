package com.spark.platform.admin.api.entity.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.entity.dict
 * @ClassName: DictItem
 * @Author: wangdingfeng
 * @Description: 字典子列表
 * @Date: 2020/3/26 9:57
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_dict_item")
@ApiModel(value = "DictItem",description = "字典子项设置")
public class DictItem extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父id")
    private Long pid;

    @ApiModelProperty(value = "字典类型")
    private String type;

    @ApiModelProperty(value = "字典标签值")
    @NotBlank(message = "字典标签值不能为空")
    private String label;

    @ApiModelProperty(value = "字典值")
    @NotBlank(message = "字典值不能为空")
    private String value;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "扩充字段1")
    private String value1;

    @ApiModelProperty(value = "扩充字段2")
    private String value2;

}
