package com.spark.platform.wx.shop.api.vo;

import com.spark.platform.wx.shop.api.enums.ShopGoodsStatusEnum;
import com.spark.platform.wx.shop.api.enums.ShopOrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/11 21:12
 * @Description:
 */
@Data
@ApiModel(value="OrderCardVo对象", description="订单列表信息Vo")
public class OrderCardVo {
    @ApiModelProperty(value = "订单ID")
    private Integer id;
    @ApiModelProperty(value = "订单类型)")
    private String orderType;
    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
    @ApiModelProperty(value = "订单状态(翻译)")
    private String orderStatusName;
    @ApiModelProperty(value = "订单编号")
    private String orderSn;
    @ApiModelProperty(value = "订单总价")
    private BigDecimal orderPrice;
    @ApiModelProperty(value = "优惠券价格")
    private BigDecimal couponPrice;
    @ApiModelProperty(value = "实际需要支付的金额")
    private BigDecimal actualPrice;
    @ApiModelProperty(value = "商品列表")
    private List<OrderGoods> goodsList;

    @Setter
    @Getter
    public static class OrderGoods {
        @ApiModelProperty(value = "商品title")
        private String goodsTitle;
        @ApiModelProperty(value = "商品图片")
        private String picUrl;
        @ApiModelProperty(value = "下单数")
        private Integer number;
        @ApiModelProperty(value = "下单单价")
        private BigDecimal price;
        @ApiModelProperty(value = "规格名称")
        private String goodsAttrVals;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        this.orderStatusName = ShopOrderStatusEnum.statusOf(orderStatus).getDesc();
    }
}
