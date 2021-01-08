package com.spark.platform.wx.shop.api.entity.user;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@Data
@Accessors(chain = true)
@TableName("shop_user_address")
@ApiModel(value="ShopUserAddress对象", description="收货地址表")
public class ShopUserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "收货人名称")
    private String name;

    @ApiModelProperty(value = "用户表的用户ID")
    @NotNull(message = "当前用户不能为空")
    private Integer userId;

    @ApiModelProperty(value = "行政区域表的省ID")
    private Integer provinceId;

    @ApiModelProperty(value = "行政区域表的市ID")
    private Integer cityId;

    @ApiModelProperty(value = "行政区域表的区县ID")
    private Integer areaId;

    @ApiModelProperty(value = "具体收货地址")
    private String address;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "是否默认地址")
    private String isDefault;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @TableLogic(value = "0",delval = "1")
    @ApiModelProperty(value = "系统状态")
    private Integer delFlag;


}
