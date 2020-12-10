package com.spark.platform.wx.shop.api.entity.user;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户浏览足迹
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@Data
@Accessors(chain = true)
@TableName("shop_user_footprint")
@ApiModel(value="ShopUserFootprint对象", description="用户浏览足迹")
public class ShopUserFootprint implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer userId;

    @ApiModelProperty(value = "产品id")
    private Integer goodsId;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @TableLogic(value = "0",delval = "1")
    @ApiModelProperty(value = "系统状态")
    private Integer delFlag;


}
