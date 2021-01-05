package com.spark.platform.wx.shop.api.entity.setting;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 首页轮播图
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("shop_setting_swiper")
@ApiModel(value="ShopSettingSwiper对象", description="首页轮播图")
public class ShopSettingSwiper extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "图片地址")
    @NotNull(message = "图片不能为空")
    private String imgUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "类型 none 无类型 goods 商品")
    private String type;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "状态 0 正常 1 停用 ")
    private String status;

    @ApiModelProperty(value = "商品标题")
    @TableField(exist = false)
    private String goodsTitle;


}
