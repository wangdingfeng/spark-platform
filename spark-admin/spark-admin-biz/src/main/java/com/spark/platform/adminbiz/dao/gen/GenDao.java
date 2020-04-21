package com.spark.platform.adminbiz.dao.gen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.adminapi.entity.gen.TableColumnInfo;
import com.spark.platform.adminapi.entity.gen.TableInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.dao.gen
 * @ClassName: GenDao
 * @Author: wangdingfeng
 * @Description: 自定生成配置
 * @Date: 2020/4/15 11:34
 * @Version: 1.0
 */
@Repository
public interface GenDao {

    /**
     * 查询数据库数据表信息
     *
     * @param tableSchema 数据库
     * @param tableName   表名
     * @return
     */
    <T> IPage<TableInfo> tableInfoPage(Page<T> page, @Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    /**
     * 查询数据库数据表信息
     *
     * @param tableSchema 数据库
     * @param tableName   表名
     * @return
     */
    List<TableColumnInfo> findTableColumnInfo(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

}
