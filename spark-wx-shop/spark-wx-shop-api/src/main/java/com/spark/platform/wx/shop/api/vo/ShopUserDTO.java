package com.spark.platform.wx.shop.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author: wangdingfeng
 * @Description: 用户信息返回
 * @Date: 2021/1/26 14:44
 */
@Data
@ApiModel(value="ShopUserDTO对象", description="api 返回用户信息")
public class ShopUserDTO {
    @ApiModelProperty(value = "主键")
    @NotNull(message = "请登录用户")
    private Integer id;
    @ApiModelProperty(value = "用户昵称或网络名称")
    private String nickname;
    @ApiModelProperty(value = "生日")
    private LocalDate birthday;
    @ApiModelProperty(value = "用户手机号码")
    private String mobile;
    @ApiModelProperty(value = "微信登录openid")
    private String wxOpenid;
    @ApiModelProperty(value = "用户类型 0 普通用户")
    private String userTypeName;
    @ApiModelProperty(value = "用户头像图片")
    private String avatar;
    @ApiModelProperty(value = "0 可用, 1 禁用, 2 注销")
    private Integer status;
    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "收藏数量")
    private int collectNum;
    @ApiModelProperty(value = "足迹数量")
    private int foorPrintNum;
    @ApiModelProperty(value = "总数量")
    private BigDecimal totalAmount = new BigDecimal(0);
}
