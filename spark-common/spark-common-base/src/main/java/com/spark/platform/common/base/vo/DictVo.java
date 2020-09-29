package com.spark.platform.common.base.vo;

import lombok.Data;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.vo
 * @ClassName: DictVo
 * @Author: wangdingfeng
 * @Description: 字典
 * @Date: 2020/9/28 16:02
 * @Version: 1.0
 */
@Data
public class DictVo {
    /**
     * 类型
     */
    private String type;
    /**
     * 标签名
     */
    private String label;
    /**
     * 标签值
     */
    private String value;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String description;
    /**
     * 扩展字段1
     */
    private String value1;
    /**
     * 扩展字段2
     */
    private String value2;
}
