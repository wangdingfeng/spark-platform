package com.spark.platform.wx.shop.api.entity.order;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 下单商品详情
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
@Data
@Accessors(chain = true)
@TableName("shop_order_goods")
@ApiModel(value="ShopOrderGoods对象", description="下单商品详情")
public class ShopOrderGoods {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "商品编号")
    private String goodsSn;

    @ApiModelProperty(value = "商品title")
    private String goodsTitle;

    @ApiModelProperty(value = "商品图片")
    private String picUrl;

    @ApiModelProperty(value = "下单数")
    private Integer number;

    @ApiModelProperty(value = "下单单价")
    private BigDecimal price;

    @ApiModelProperty(value = "下单总价")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "规格")
    private String goodsAttrValIds;

    @ApiModelProperty(value = "规格名称")
    private String goodsAttrVals;


}
