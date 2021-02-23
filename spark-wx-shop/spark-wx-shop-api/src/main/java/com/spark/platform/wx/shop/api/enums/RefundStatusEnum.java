package com.spark.platform.wx.shop.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.enums
 * @ClassName: RefundStatusEnum
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/7 14:50
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum RefundStatusEnum {

    REQUEST(0,"申请中"),
    COMPLETE(1,"退款完成"),
    REFUSED(2,"拒绝退款");

    private Integer status;
    private String desc;
}
