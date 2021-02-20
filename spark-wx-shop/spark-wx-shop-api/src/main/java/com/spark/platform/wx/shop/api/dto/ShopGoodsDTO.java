package com.spark.platform.wx.shop.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.dto
 * @ClassName: ShopGoodsDTO
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/12/15 13:42
 * @Version: 1.0
 */
@Data
@ApiModel(value="ShopGoodsDTO对象", description="")
public class ShopGoodsDTO {
    @ApiModelProperty(value = "主键")
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

}
