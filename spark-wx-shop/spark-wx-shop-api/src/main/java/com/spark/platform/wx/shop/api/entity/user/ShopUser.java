package com.spark.platform.wx.shop.api.entity.user;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * shop会员管理
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ShopUser对象", description="shop会员管理")
public class ShopUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "性别：0 未知， 1男， 1 女")
    private String gender;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "最近一次登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "最近一次登录IP地址")
    private String lastLoginIp;

    @ApiModelProperty(value = "用户类型 0 普通用户")
    private Integer userType;

    @ApiModelProperty(value = "用户昵称或网络名称")
    private String nickname;

    @ApiModelProperty(value = "用户手机号码")
    private String mobile;

    @ApiModelProperty(value = "用户头像图片")
    private String avatar;

    @ApiModelProperty(value = "微信登录openid")
    private String wxOpenid;

    @ApiModelProperty(value = "0 可用, 1 禁用, 2 注销")
    private Integer status;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic(value = "0",delval = "1")
    @ApiModelProperty(value = "系统状态")
    private Integer delFlag;

    @ApiModelProperty(value = "token")
    private transient String token;


}
