package com.spark.platform.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.enums
 * @ClassName: DelFlagEnum
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/3 17:08
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum DelFlagEnum {

    NORMAL(0,"正常"),
    DELETE(1,"删除");

    private Integer value;

    private String desc;
}
