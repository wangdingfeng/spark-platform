package com.spark.platform.wx.shop.api.entity.goods;

import java.io.Serializable;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品属性搭配
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
@Data
@Accessors(chain = true)
@TableName("shop_goods_sku")
@ApiModel(value="ShopGoodsSku对象", description="商品属性搭配")
public class ShopGoodsSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品主键")
    private Integer goodsId;

    @ApiModelProperty(value = "属性值搭配,逗号")
    private String attrValIds;

    @ApiModelProperty(value = "属性值搭配,逗号")
    private String attrVals;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "商品编码")
    private String skuCode;

    @ApiModelProperty(value = "活动价格")
    private BigDecimal activityPrice;


}
