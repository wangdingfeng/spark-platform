package com.spark.platform.wx.shop.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.vo
 * @ClassName: SwiperVo
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/3 17:22
 * @Version: 1.0
 */
@Data
@ApiModel(value = "SwiperVo对象", description = "轮播图")
public class SwiperVo {
    @ApiModelProperty(value = "图片地址")
    private String imgUrl;
    @ApiModelProperty(value = "类型 none 无类型 goods 商品")
    private String type;
    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;
}
