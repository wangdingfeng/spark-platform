package com.spark.platform.wx.shop.api.entity.marketing;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 拼团用户列表
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ShopPinkUser对象", description="拼团用户列表")
public class ShopPinkUser{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "用户主键")
    private Integer userId;

    @ApiModelProperty(value = "商品主键")
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsTitle;

    @ApiModelProperty(value = "订单IDs")
    private String orderIds;

    @ApiModelProperty(value = "几人团")
    private Integer people;

    @ApiModelProperty(value = "几人参加")
    private Integer countPeople;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "状态 1 拼团中 2 拼团成功 3 拼团失败")
    private Integer status;


}
