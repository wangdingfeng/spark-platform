package com.spark.platform.wx.shop.api.entity.marketing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.spark.platform.common.base.entity.BaseEntity;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 拼团产品
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ShopPinkGoods对象", description="拼团产品")
public class ShopPinkGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "拼团有效时间 小时")
    private Integer effectiveTime;

    @ApiModelProperty(value = "拼团价格")
    private BigDecimal price;

    @ApiModelProperty(value = "参团人数")
    private Integer people;

    @ApiModelProperty(value = "限购")
    private Integer quota;

    @ApiModelProperty(value = "状态 0 关闭 1开始")
    private Boolean status;

    @ApiModelProperty(value = "商品标题")
    private transient String goodsTitle;
    @ApiModelProperty(value = "商品主图")
    private transient String homePic;
    @ApiModelProperty(value = "商品原价")
    private transient BigDecimal minPrice;

    @ApiModelProperty(value = "商品SKUs")
    @TableField(exist = false)
    private List<ShopGoodsSku> goodsSkus;


}
