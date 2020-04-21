package com.spark.platform.adminapi.entity.gen;

import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.entity.gen
 * @ClassName: TableInfo
 * @Author: wangdingfeng
 * @Description: 数据库表信息
 * @Date: 2020/4/15 11:21
 * @Version: 1.0
 */
@Data
public class TableInfo {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 建表引擎
     */
    private String engine;
    /**
     * 表备注
     */
    private String tableComment;
    /**
     * 数据行数
     */
    private Integer tableRows;
    /**
     * 自增主键当量值
     */
    private String autoIncrement;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
