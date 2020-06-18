package com.spark.platform.adminapi.entity.authority;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.entity.authority
 * @ClassName: Menu
 * @Description: 菜单
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_menu")
@ApiModel(value = "Menu",description = "菜单设置")
public class Menu extends BaseEntity {

    /**
     * id
     * */
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "父级菜单主键")
    private Long pid;

    @ApiModelProperty(value = "类型 0 菜单 1 按钮")
    private String type;

    @ApiModelProperty(value = "是否外链")
    @JsonProperty("iFrame")
    private boolean iFrame;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "权限")
    private String permission;

    @ApiModelProperty(value = "是否隐藏")
    private boolean hidden;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableField(exist = false)
    private List<Menu> children;

}
