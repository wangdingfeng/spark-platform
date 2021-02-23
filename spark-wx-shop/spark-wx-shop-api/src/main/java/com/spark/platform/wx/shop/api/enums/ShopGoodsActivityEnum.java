package com.spark.platform.wx.shop.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.enums
 * @ClassName: ShopGoodsActivityEnum
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/6 15:40
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ShopGoodsActivityEnum {

    NORMAL("0","正常"),
    SECKILL("1","秒杀"),
    PINK("2","团购");

    private String status;
    private String desc;
}
