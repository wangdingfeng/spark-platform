package com.spark.platform.wx.shop.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/9 15:38
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum CouponUserStatusEnum {

    NO_USE(0,"未使用"),
    HAVE_USE(1,"已使用"),
    OVERDUE(2,"已过期");

    private Integer status;
    private String desc;
}
