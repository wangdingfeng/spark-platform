package com.spark.platform.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.enums
 * @ClassName: FileStatusEnum
 * @Author: wangdingfeng
 * @Description: 文件状态
 * @Date: 2020/11/11 11:24
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum FileStatusEnum {

    NOT_BIND("0", "未绑定"),
    BIND("1", "绑定");

    private String status;

    private String describe;
}
