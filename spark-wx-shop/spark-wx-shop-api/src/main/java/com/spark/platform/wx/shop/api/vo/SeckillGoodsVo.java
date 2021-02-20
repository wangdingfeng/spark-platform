package com.spark.platform.wx.shop.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @athor: wangdingfeng
 * @description: 秒杀商品
 * @date: 2021/1/20 14:21
 */
@Data
@ApiModel(value = "SeckillGoodsVo对象", description = "秒杀商品Vo")
public class SeckillGoodsVo {
    @ApiModelProperty(value = "秒杀商品ID")
    private Integer seckillGoodsId;
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
    @ApiModelProperty(value = "秒杀价格")
    private BigDecimal price;
    @ApiModelProperty(value = "秒杀销量")
    private Integer sales;
    @ApiModelProperty(value = "是否限购")
    private Boolean isQuota;
    @ApiModelProperty(value = "限购总数")
    private Integer quota;
}
