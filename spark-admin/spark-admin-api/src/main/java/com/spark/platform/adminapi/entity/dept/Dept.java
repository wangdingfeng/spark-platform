package com.spark.platform.adminapi.entity.dept;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spark.platform.adminapi.entity.authority.Menu;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.entity.dept
 * @ClassName: Dept
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_dept")
@ApiModel(value = "Dept",description = "部门设置")
public class Dept extends BaseEntity {

    /**
     * id
     * */
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "父部门id")
    private Long pid;

    @ApiModelProperty(value = "父部门层级")
    private String pids;

    @ApiModelProperty(value = "简称")
    private String simpleName;

    @ApiModelProperty(value = "全称")
    private String fullName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "部门类型(0 公司1部门)")
    private Integer deptType;

    @TableField(exist = false)
    private List<Menu> children;

}
