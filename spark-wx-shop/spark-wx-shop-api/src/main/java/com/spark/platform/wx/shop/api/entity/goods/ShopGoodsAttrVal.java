package com.spark.platform.wx.shop.api.entity.goods;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品属性值
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
@Data
@Accessors(chain = true)
@TableName("shop_goods_attr_val")
@ApiModel(value="ShopGoodsAttrVal对象", description="商品属性值")
public class ShopGoodsAttrVal {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "attr_val_id", type = IdType.AUTO)
    private Integer attrValId;

    @ApiModelProperty(value = "属性ID")
    private Integer attrId;

    @ApiModelProperty(value = "属性值")
    private String attrVal;

    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    @ApiModelProperty(value = "图片")
    private String pic;


}
