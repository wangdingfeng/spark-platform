package com.spark.platform.adminapi.entity.authority;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
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

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父级菜单主键
     */
    private Long pid;

    /**
     * 类型 0 菜单 1 按钮
     */
    private String type;
    /**
     * 是否外链
     */
    private boolean iFrame;

    /**
     * 路径
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 权限
     */
    private String permission;
    /**
     * 是否隐藏
     */
    private boolean hidden;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;

    @TableField(exist = false)
    private List<Menu> children;

}
