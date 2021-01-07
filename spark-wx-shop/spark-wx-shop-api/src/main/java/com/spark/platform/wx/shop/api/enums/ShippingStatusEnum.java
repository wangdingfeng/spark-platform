package com.spark.platform.wx.shop.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.enums
 * @ClassName: ShippingStatusEnum
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/7 14:42
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ShippingStatusEnum {

    WAIT_SEND(0,"待发货"),
    SEND(1,"已发货"),
    COMPLETE(2,"已收货"),
    REFUND(3,"退货");

    private Integer status;
    private String desc;
}
