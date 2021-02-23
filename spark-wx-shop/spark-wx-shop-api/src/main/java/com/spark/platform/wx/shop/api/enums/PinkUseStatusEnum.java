package com.spark.platform.wx.shop.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.enums
 * @ClassName: PinkUseStatusEnum
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/12 13:25
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum PinkUseStatusEnum {

    ING(1,"拼团中"),
    SUCCESS(2,"拼团成功"),
    FAIL(2,"拼团失败");

    private Integer status;
    private String desc;
}
