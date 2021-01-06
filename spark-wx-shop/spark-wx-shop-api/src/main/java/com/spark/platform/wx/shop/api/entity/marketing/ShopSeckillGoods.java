package com.spark.platform.wx.shop.api.entity.marketing;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品秒杀配置列表
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-05
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ShopSeckillGoods对象", description="商品秒杀配置列表")
public class ShopSeckillGoods {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品主键")
    private Integer goodsId;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "秒杀价格")
    private BigDecimal price;

    @ApiModelProperty(value = "秒杀销量")
    private Integer sales;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否限购")
    private Boolean isQuota;

    @ApiModelProperty(value = "限购总数")
    private Integer quota;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic(value = "0",delval = "1")
    @ApiModelProperty(value = "系统状态")
    private Integer delFlag;

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
