package com.spark.platform.wx.shop.api.entity.order;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单管理
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ShopOrder对象", description="订单管理")
public class ShopOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "会员ID")
    private Integer userId;

    @ApiModelProperty(value = "订单类型 0 普通订单 1 团购订单 2 秒杀订单")
    private String orderType;

    @ApiModelProperty(value = "订单状态 0 待付款 1 已取消 2 已付款 3 已发货  4 退款 5 完成 6 待评价")
    private Integer orderStatus;

    @ApiModelProperty(value = "发货状态 0 待发货 1 已发货 2 已收货 3 退货")
    private Integer shippingStatus;

    @ApiModelProperty(value = "退款状态 0 申请中 1 退款完成 2 拒绝退款")
    private Integer refundStatus;

    @ApiModelProperty(value = "业务id 对应的是 团购列表ID 秒杀商品的ID")
    private Long bizId;

    @ApiModelProperty(value = "收件人")
    private String consignee;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "运费")
    private BigDecimal shippingFee;

    @ApiModelProperty(value = "支付Name")
    private String payName;

    @ApiModelProperty(value = "支付ID")
    private Integer payId;

    @ApiModelProperty(value = "实际需要支付的金额")
    private BigDecimal actualPrice;

    @ApiModelProperty(value = "订单总价")
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "用户确认收货时间")
    private LocalDateTime confirmTime;

    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "完成时间")
    private LocalDateTime completeTime;

    @ApiModelProperty(value = "配送费用")
    private BigDecimal freightPrice;

    @ApiModelProperty(value = "使用的优惠券id")
    private Integer couponId;

    @ApiModelProperty(value = "优惠券价格")
    private BigDecimal couponPrice;

    @ApiModelProperty(value = "用户备注")
    private String userRemarks;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyDate;

    @ApiModelProperty(value = "订单商品")
    @TableField(exist = false)
    private List<ShopOrderGoods> goodsList;

    @ApiModelProperty(value = "用户信息")
    @TableField(exist = false)
    private ShopUser user;

    @ApiModelProperty(value = "物流信息")
    @TableField(exist = false)
    private List<ShopOrderExpress> expressList;


}
