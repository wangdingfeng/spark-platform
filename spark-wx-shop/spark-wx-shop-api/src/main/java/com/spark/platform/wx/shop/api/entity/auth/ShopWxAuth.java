package com.spark.platform.wx.shop.api.entity.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 微信小程序授权信息
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ShopWxAuth对象", description="微信小程序授权信息")
public class ShopWxAuth extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "appID")
    @NotNull(message = "appID不允许为空！")
    private String appId;

    @ApiModelProperty(value = "secret")
    @NotNull(message = "secret不允许为空！")
    private String secret;

    @ApiModelProperty(value = "oauth2 clientId")
    private String clientId;

}
