package com.spark.platform.wx.shop.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.enums
 * @ClassName: ShopOrderStatusEnum
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/7 14:29
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ShopOrderStatusEnum {

    WAIT_PAY(0,"待付款"),
    CANCEL(1,"已取消"),
    PAY(2,"已付款"),
    SEND(3,"已发货"),
    CONFIRM_SEND(4,"用户确认收货"),
    REFUND(5,"退款"),
    COMPLETE(6,"完成"),
    EVALUATION(7,"待评价");

    private Integer status;
    private String desc;
}
