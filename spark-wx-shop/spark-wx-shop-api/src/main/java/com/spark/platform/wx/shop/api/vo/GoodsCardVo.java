package com.spark.platform.wx.shop.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.vo
 * @ClassName: GoodsCardVo
 * @Author: wangdingfeng
 * @Description: 商品卡片
 * @Date: 2020/12/23 14:06
 * @Version: 1.0
 */
@Data
@ApiModel(value="GoodsCardVo对象", description="商品卡片查询")
public class GoodsCardVo {
    @ApiModelProperty(value = "商品主键")
    private String goodsId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "简介")
    private String brief;
    @ApiModelProperty(value = "图片")
    private String homePic;
    @ApiModelProperty(value = "零售价")
    private BigDecimal retailPrice;
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    @ApiModelProperty(value = "销售件数")
    private Integer saleNum;
    @ApiModelProperty(value = "库存数")
    private Integer stock;
}
