package com.spark.platform.wx.shop.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalTime;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/10 14:53
 * @Description:
 */
@Data
@ApiModel(value="SeckillVo对象", description="秒杀配置信息")
public class SeckillVo {

    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "秒杀名称")
    private String name;
    @ApiModelProperty(value = "开始时间")
    private LocalTime startTime;
    @ApiModelProperty(value = "结束时间")
    private LocalTime endTime;
    @ApiModelProperty(value = "图片")
    private String image;
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
