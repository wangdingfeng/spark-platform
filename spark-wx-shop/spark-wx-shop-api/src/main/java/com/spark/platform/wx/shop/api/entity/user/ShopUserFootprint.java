package com.spark.platform.wx.shop.api.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy.MM.dd")
    private LocalDateTime createDate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic(value = "0",delval = "1")
    @ApiModelProperty(value = "系统状态")
    private Integer delFlag;

    @ApiModelProperty(value = "商品标题")
    private transient String goodsTitle;
    @ApiModelProperty(value = "商品图片")
    private transient String homePic;
    @ApiModelProperty(value = "商品价格")
    private transient BigDecimal price;


}
