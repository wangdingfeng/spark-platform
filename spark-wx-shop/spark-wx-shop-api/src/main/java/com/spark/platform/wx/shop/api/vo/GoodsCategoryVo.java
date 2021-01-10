package com.spark.platform.wx.shop.api.vo;

import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/9 14:51
 * @Description:
 */
@Data
@ApiModel(value="GoodsCategoryVo对象", description="商品分类")
public class GoodsCategoryVo {
    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "父id")
    private Integer pid;
    @ApiModelProperty(value = "分类名称")
    private String text;
    @ApiModelProperty(value = "分类图")
    private String pic;
    @ApiModelProperty(value = "是否最末级")
    private Boolean isLeaf;
    @ApiModelProperty(value = "子数据")
    private List<GoodsCategoryVo> children;
}
