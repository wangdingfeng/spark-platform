package com.spark.platform.wx.shop.api.entity.goods;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品评价
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
@Data
@Accessors(chain = true)
@TableName("shop_goods_comment")
@ApiModel(value="ShopGoodComment对象", description="商品评价")
public class ShopGoodsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "会员ID")
    private Integer userId;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "描述相符")
    private Integer star;

    @ApiModelProperty(value = "图片")
    private String pic;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic(value = "0",delval = "1")
    @ApiModelProperty(value = "系统状态")
    private Integer delFlag;

    @ApiModelProperty(value = "商品编号")
    private transient String goodsSn;
    @ApiModelProperty(value = "会员名")
    private transient String userName;


}
