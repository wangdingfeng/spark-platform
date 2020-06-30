package com.spark.platform.common.datasource.emuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.datasource.emuns
 * @ClassName: DataScopeTypeEnum
 * @Author: wangdingfeng
 * @Description: 数据权限枚举
 * @Date: 2020/6/8 17:13
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum {

    /**
     * 全部
     */
    ALL(1, "全部"),
    /**
     * 本级
     */
    THIS_LEVEL(2, "本级"),

    /**
     * 本级以及子级
     */
    THIS_LEVEL_CHILDREN(3,"本级以及子级"),
    /**
     * 自定义
     */
    CUSTOMIZE(4, "自定义");

    private int type;

    private String description;


    public static DataScopeTypeEnum valueOf(int type) {
        for(DataScopeTypeEnum typeVar : DataScopeTypeEnum.values()) {
            if(typeVar.getType() == type) {
                return typeVar;
            }
        }
        return  ALL;
    }


}

