package com.spark.platform.wx.shop.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.dto
 * @ClassName: SubmitOrderDTO
 * @Author: wangdingfeng
 * @Description: 提交订单DTO
 * @Date: 2021/1/11 10:43
 * @Version: 1.0
 *
 */
@Data
@ApiModel(value="SubmitOrderDTO对象", description="提交订单管理")
public class SubmitOrderDTO {
    @ApiModelProperty(value = "用户ID")
    @NotNull(message = "用户未登录!")
    private Integer userId;
    @ApiModelProperty(value = "订单类型")
    @NotNull(message = "订单类型不能为空!")
    private String orderType;
    @ApiModelProperty(value = "收货地址ID")
    @NotNull(message = "收货地址不能为空!")
    private Integer addressId;
    @ApiModelProperty(value = "秒杀商品ID")
    private Integer seckillGoodsId;
    @ApiModelProperty(value = "拼团商品ID")
    private Integer pinkGoodsId;
    @ApiModelProperty(value = "用户优惠券ID")
    private Integer couponUserId;
    @ApiModelProperty(value = "用户购物车ID")
    private Integer userCartId;
    @ApiModelProperty(value = "用户备注")
    private String userRemarks;
    @ApiModelProperty(value = "商品表")
    private List<OrderGoods> orderGoods;

    @Getter
    @Setter
    public static class OrderGoods{
        @ApiModelProperty(value = "商品ID")
        private Integer goodsId;
        @ApiModelProperty(value = "下单数")
        private Integer number;
        @ApiModelProperty(value = "下单单价")
        private BigDecimal price;
        @ApiModelProperty(value = "规格")
        private String goodsAttrValIds;
    }

}
