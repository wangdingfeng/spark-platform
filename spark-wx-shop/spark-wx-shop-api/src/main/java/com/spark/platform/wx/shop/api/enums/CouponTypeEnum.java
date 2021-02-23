package com.spark.platform.wx.shop.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.enums
 * @ClassName: CouponTypeEnum
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/11 15:36
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum CouponTypeEnum {

    NEW("new","新人券"),
    MONEY("money","现金券"),
    FULLRED("fullRed","满减券"),
    DISCOUNT("discount","折扣券");

    private String type;
    private String desc;

    public static String typeOf(String type) {
        for(CouponTypeEnum typeEnum : values()){
            if(typeEnum.getType().equals(type)){
                return typeEnum.desc;
            }
        }
        return "";
    }
}
