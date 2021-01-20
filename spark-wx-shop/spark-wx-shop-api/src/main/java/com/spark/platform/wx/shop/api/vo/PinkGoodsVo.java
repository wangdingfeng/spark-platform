package com.spark.platform.wx.shop.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: wangdingfeng
 * @Description: 拼团商品
 * @Date: 2021/1/20 14:18
 */
@Data
@ApiModel(value="PinkGoodsVo对象", description="拼团商品Vo")
public class PinkGoodsVo {
    @ApiModelProperty(value = "拼团商品ID")
    private Integer pinkGoodsId;
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
}
