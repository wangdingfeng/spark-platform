package com.spark.platform.wx.shop.api.entity.specs;

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

import java.util.List;

/**
 * <p>
 * 商品规格属性
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("shop_specs_attr")
@ApiModel(value="ShopSpecsAttr对象", description="商品规格属性")
public class ShopSpecsAttr extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Integer attrId;

    @ApiModelProperty(value = "属性名称")
    private String attrName;

    @ApiModelProperty(value = "组件类型")
    private String attrType;

    @ApiModelProperty(value = "是否上传图片")
    private String isPic;

    @ApiModelProperty(value = "属性值")
    @TableField(exist = false)
    private List<ShopSpecsAttrVal> attrVals;


}
