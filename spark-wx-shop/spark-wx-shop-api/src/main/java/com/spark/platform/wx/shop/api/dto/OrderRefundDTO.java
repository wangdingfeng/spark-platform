package com.spark.platform.wx.shop.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.dto
 * @ClassName: OrderRefundDTo
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/12 14:33
 * @Version: 1.0
 */
@Data
@ApiModel(value="OrderRefundDTO对象", description="提交退款")
public class OrderRefundDTO {
    @ApiModelProperty(value = "用户ID")
    @NotNull(message = "用户未登录!")
    private Integer userId;
    @ApiModelProperty(value = "订单ID")
    @NotNull(message = "请选择订单!")
    private Integer orderId;
    @ApiModelProperty(value = "订单商品ID")
    @NotNull(message = "请选择订单商品!")
    private Integer orderGoodsId;
    @ApiModelProperty(value = "数量")
    @NotNull(message = "请填写数量!")
    private Integer num;
    @ApiModelProperty(value = "退款图片")
    private String img;
    @ApiModelProperty(value = "退款原因")
    @NotNull(message = "请填写退款原因!")
    private String reason;

}
