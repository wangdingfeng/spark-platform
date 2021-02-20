package com.spark.platform.wx.shop.api.entity.specs;

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
 * 商品规格属性值
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("shop_specs_attr_val")
@ApiModel(value="ShopSpecsAttrVal对象", description="商品规格属性值")
public class ShopSpecsAttrVal extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "attr_val_id", type = IdType.AUTO)
    private Integer attrValId;

    @ApiModelProperty(value = "属性ID")
    private Integer attrId;

    @ApiModelProperty(value = "属性值")
    private String attrVal;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
