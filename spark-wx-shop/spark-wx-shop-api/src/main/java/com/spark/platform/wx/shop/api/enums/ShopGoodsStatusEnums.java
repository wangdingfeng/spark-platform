package com.spark.platform.wx.shop.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wangdingfeng
 * @Date: 2020/12/18 20:36
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum ShopGoodsStatusEnums {

    WAITING_PUBLISH("0","待上架"),
    PUBLISH("1","已上架"),
    OFF_PUBLISH("2","下架");

    private String status;
    private String desc;
}
