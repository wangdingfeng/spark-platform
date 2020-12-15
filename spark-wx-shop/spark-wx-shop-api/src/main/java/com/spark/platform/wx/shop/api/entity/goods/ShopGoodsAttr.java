package com.spark.platform.wx.shop.api.entity.goods;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品属性
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
@Data
@Accessors(chain = true)
@TableName("shop_goods_attr")
@ApiModel(value="ShopGoodsAttr对象", description="商品属性")
public class ShopGoodsAttr {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性ID")
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Integer attrId;

    @ApiModelProperty(value = "商品主键")
    private Integer goodsId;

    @ApiModelProperty(value = "属性名称")
    private String attrName;

    @ApiModelProperty(value = "是否需要图片")
    private String isPic;


}
