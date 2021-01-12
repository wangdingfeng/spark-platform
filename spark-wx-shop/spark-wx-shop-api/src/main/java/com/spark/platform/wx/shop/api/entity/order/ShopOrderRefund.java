package com.spark.platform.wx.shop.api.entity.order;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.spark.platform.common.base.entity.BaseEntity;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 退款管理
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
@Data
@Accessors(chain = true)
@TableName("shop_order_refund")
@ApiModel(value="ShopOrderRefund对象", description="退款管理")
public class ShopOrderRefund {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "退款单号")
    private String refundSn;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "退款用户")
    private Integer userId;

    @ApiModelProperty(value = "退款商品ID")
    private Integer orderGoodsId;

    @ApiModelProperty(value = "申请数量")
    private Integer num;

    @ApiModelProperty(value = "退款状态 0 申请中 1 退款完成 2 拒绝退款")
    private Integer refundStatus;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "退款时间")
    private LocalDateTime refundTime;

    @ApiModelProperty(value = "退款图片")
    private String img;

    @ApiModelProperty(value = "退款原因")
    private String reason;

    @ApiModelProperty(value = "拒绝退款原因")
    private String refusedReason;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyDate;

    @ApiModelProperty(value = "订单商品")
    @TableField(exist = false)
    private ShopOrderGoods orderGoods;

    @ApiModelProperty(value = "用户信息")
    @TableField(exist = false)
    private ShopUser user;


}
