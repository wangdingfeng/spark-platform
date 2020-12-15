package com.spark.platform.wx.shop.api.entity.goods;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ShopGoods对象", description="商品表")
public class ShopGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品编号")
    private String goodsSn;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "商品分类")
    private String categoryIds;

    @ApiModelProperty(value = "商品关键词")
    private String keywords;

    @ApiModelProperty(value = "商品简介")
    private String brief;

    @ApiModelProperty(value = "首页图片")
    private String homePic;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "是否新品")
    private String isNew;

    @ApiModelProperty(value = "是否人气推荐")
    private String isHot;

    @ApiModelProperty(value = "零售价格")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "批发价格")
    private BigDecimal wholesalePrice;

    @ApiModelProperty(value = "推广佣金")
    private BigDecimal brokeragePrice;

    @ApiModelProperty(value = "描述")
    private String detail;

    @ApiModelProperty(value = "排序")
    private Integer sortOrder;

    @ApiModelProperty(value = "状态 0 待上架 1 上架 2 下架")
    private String status;


}
