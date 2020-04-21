package com.spark.platform.adminbiz.service.gen;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.adminapi.entity.gen.TableColumnInfo;
import com.spark.platform.adminapi.vo.GeneratorConfigVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.gen
 * @ClassName: GenService
 * @Author: wangdingfeng
 * @Description: 自动生成配置
 * @Date: 2020/4/15 11:52
 * @Version: 1.0
 */
public interface GenService {
    /**
     * 查询数据库表
     *
     * @param tableName
     * @return
     */
    IPage tableInfoPage(Page page, String tableName);

    /**
     * 查询数据库表
     *
     * @param tableName
     * @return
     */
    List<TableColumnInfo> findTableColumnInfo(String tableName);

    /**
     * 生成代码
     * @param generatorConfigVo
     * @return
     */
    String generatorCode(GeneratorConfigVo generatorConfigVo);

    /**
     * 下载代码
     * @param generatorConfigVo
     */
    void downLoadCodes(GeneratorConfigVo generatorConfigVo, HttpServletResponse response);
}
