package com.spark.platform.wx.shop.api.entity.goods;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("shop_goods_category")
@ApiModel(value="ShopGoodsCategory对象", description="商品分类")
public class ShopGoodsCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using= ToStringSerializer.class)
    private Integer id;

    @ApiModelProperty(value = "父id")
    @NotNull(message = "请选择父节点")
    private Integer pid;

    @ApiModelProperty(value = "父ids")
    private String pids;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类图")
    private String pic;

    @ApiModelProperty(value = "分类")
    private Integer sort;

    @ApiModelProperty(value = "子数据")
    @TableField(exist = false)
    private List<ShopGoodsCategory> children;



}
