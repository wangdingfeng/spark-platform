package com.spark.platform.wx.shop.api.entity.marketing;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券发放记录
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-04
 */
@Data
@Accessors(chain = true)
@TableName("shop_coupon_user")
@ApiModel(value="ShopCouponUser对象", description="优惠券发放记录")
public class ShopCouponUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "优惠券ID")
    private Integer couponId;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "使用时间")
    private LocalDateTime useTime;

    @ApiModelProperty(value = "状态 0  未使用 1 已使用 2 已过期")
    private Integer status;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyDate;


}
