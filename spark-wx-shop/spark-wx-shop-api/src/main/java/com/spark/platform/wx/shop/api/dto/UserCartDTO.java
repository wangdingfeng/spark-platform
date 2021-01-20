package com.spark.platform.wx.shop.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: wangdingfeng
 * @description: 用户购物车请求 DTO
 * @date: 2021/1/20 16:42
 */
@Data
@ApiModel(value="UserCartDTO对象", description="提交童虎购物车信息")
public class UserCartDTO {
    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "会员ID")
    @NotNull(message = "会员不能为空")
    private Integer userId;
    @ApiModelProperty(value = "商品ID")
    @NotNull(message = "商品ID不能为空")
    private Integer goodsId;
    @ApiModelProperty(value = "规格搭配")
    @NotNull(message = "规格搭配不能为空")
    private String attrValIds;
    @ApiModelProperty(value = "规格搭配翻译")
    private String attrVals;
    @ApiModelProperty(value = "数量")
    private Integer num;
}
