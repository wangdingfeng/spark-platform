package com.spark.platform.wx.shop.api.entity.goods;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.spark.platform.common.base.entity.BaseEntity;
import com.spark.platform.wx.shop.api.entity.specs.ShopSpecsAttr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ShopGoods对象", description="商品表")
public class ShopGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品编号")
    @NotNull(message = "商品编号不能为空")
    private String goodsSn;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "商品分类")
    @NotNull(message = "商品分类不能为空")
    private String categoryIds;

    @ApiModelProperty(value = "商品分类Name")
    private String categoryNames;
    @NotNull(message = "商品标题不能为空")

    @ApiModelProperty(value = "商品关键词")
    private String keywords;

    @ApiModelProperty(value = "商品简介")
    private String brief;

    @ApiModelProperty(value = "首页图片")
    private String homePic;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "是否新品")
    private String isNew;

    @ApiModelProperty(value = "是否人气推荐")
    private String isHot;

    @ApiModelProperty(value = "零售价格")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "批发价格")
    private BigDecimal wholesalePrice;

    @ApiModelProperty(value = "推广佣金")
    private BigDecimal brokeragePrice;

    @ApiModelProperty(value = "规格")
    @NotNull(message = "商品规格不能为空")
    private String specs;

    @ApiModelProperty(value = "描述")
    private String detail;

    @ApiModelProperty(value = "排序")
    private Integer sortOrder;

    @ApiModelProperty(value = "状态 0 待上架 1 上架 2 下架")
    private String status;

    @ApiModelProperty(value = "属性值")
    @TableField(exist = false)
    private List<ShopGoodsAttr> shopGoodsAttrs;

    @ApiModelProperty(value = "价格库存")
    @TableField(exist = false)
    private List<ShopGoodsSku> shopGoodsSkus;


    public String[] getCategoryIdsArg() {
        return StringUtils.isNotBlank(this.categoryIds) ? categoryIds.split(StringPool.COMMA) : new String[0];
    }

    public void setCategoryIdsArg(String[] categoryIdsArg) {
        this.categoryIds = StringUtils.join(categoryIdsArg,StringPool.COMMA);
    }

    public String[] getKeywordsArg() {
        return StringUtils.isNotBlank(this.keywords) ? keywords.split(StringPool.COMMA) : new String[0];
    }

    public void setKeywordsArg(String[] keywordsArg) {
        this.keywords = StringUtils.join(keywordsArg,StringPool.COMMA);
    }

    public String[] getSpecsArg() {
        return StringUtils.isNotBlank(this.specs) ? specs.split(StringPool.COMMA) : new String[0];
    }

    public void setSpecsArg(String[] specsArg) {
        this.specs = StringUtils.join(specsArg,StringPool.COMMA);
    }
}
