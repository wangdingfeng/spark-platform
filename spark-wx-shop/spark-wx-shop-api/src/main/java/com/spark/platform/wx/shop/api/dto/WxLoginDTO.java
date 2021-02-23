package com.spark.platform.wx.shop.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: wangdingfeng
 * @Date: 2020/12/13 21:09
 * @Description: 登录
 */
@Data
@ApiModel(value="LoginDTO对象", description="登录获取用户的DTO")
public class WxLoginDTO {
    @ApiModelProperty(value = "小程序appId")
    @NotNull(message = "appId不能为空")
    private String appId;
    @ApiModelProperty(value = "登录时获取的code")
    @NotNull(message = "js_code不能为空")
    private String jsCode;
    @ApiModelProperty(value = "用户昵称或网络名称")
    private String nickname;
    @ApiModelProperty(value = "用户手机号码")
    private String mobile;
    @ApiModelProperty(value = "用户头像图片")
    private String avatar;
}
