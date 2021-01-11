package com.spark.platform.wx.shop.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/11 21:58
 * @Description:
 */
@Data
@ApiModel(value="ShopGoodsQueryDTO对象", description="商品查询")
public class ShopOrderQueryDTO {
    @ApiModelProperty(value = "订单编号")
    private String orderSn;
    @ApiModelProperty(value = "会员ID")
    private Integer userId;
    @ApiModelProperty(value = "订单类型 0 普通订单 1 团购订单 2 秒杀订单")
    private String orderType;
    @ApiModelProperty(value = "订单状态 0 待付款 1 已取消 2 已付款 3 已发货 4 用户确认收货 5 退款 6 完成 7 待评价")
    private Integer orderStatus;
    @ApiModelProperty(value = "商品名称")
    private String goodsTitle;
    @ApiModelProperty(value = "页码")
    private long current = 1;
    @ApiModelProperty(value = "分页")
    private long size = 20;
}
