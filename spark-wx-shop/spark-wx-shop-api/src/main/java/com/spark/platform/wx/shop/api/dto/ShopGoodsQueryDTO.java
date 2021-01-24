package com.spark.platform.wx.shop.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.dto
 * @ClassName: ShopGoodsQueryDTO
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/12/23 11:57
 * @Version: 1.0
 */
@Data
@ApiModel(value="ShopGoodsQueryDTO对象", description="商品查询")
public class ShopGoodsQueryDTO {
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "分类")
    private String categoryIds;
    @ApiModelProperty(value = "是否新品")
    private String isNew;
    @ApiModelProperty(value = "关键词")
    private String keywords;
    @ApiModelProperty(value = "是否人气推荐")
    private String isHot;
    @ApiModelProperty(value = "页码")
    private long current = 1;
    @ApiModelProperty(value = "分页")
    private long size = 20;
    @ApiModelProperty(value = "排序")
    private String orderBy;
    @ApiModelProperty(value = "是否升序")
    private boolean isAsc;
}
