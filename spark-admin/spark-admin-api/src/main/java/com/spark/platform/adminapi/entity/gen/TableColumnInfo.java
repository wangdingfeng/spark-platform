package com.spark.platform.adminapi.entity.gen;

import lombok.Data;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.entity.gen
 * @ClassName: TableColumnInfo
 * @Author: wangdingfeng
 * @Description: 表字段信息
 * @Date: 2020/4/15 11:41
 * @Version: 1.0
 */
@Data
public class TableColumnInfo {
    /**
     * 数据库
     */
    private String tableSchema;
    /**
     * 列名
     */
    private String columnName;
    /**
     *数据类型
     */
    private String columnType;
    /**
     * 是否为空
     */
    private String isNullable;
    /**
     * 默认值
     */
    private String columnDefault;
    /**
     * 备注
     */
    private String columnComment;
    /**
     * 索引类型
     */
    private String columnKey;
    /**
     * 主键自增
     */
    private String extra;
}
