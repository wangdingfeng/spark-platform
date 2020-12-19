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

    /**
     * 操作类型
     * @param status
     * @return
     */
    public static ShopGoodsStatusEnums statusOf(String status) {
        for(ShopGoodsStatusEnums actionEnum : values()){
            if(actionEnum.getStatus().equals(status)){
                return actionEnum;
            }
        }
        throw new RuntimeException("没有找到对应的枚举");
    }
}
