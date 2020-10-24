package com.spark.platform.adminbiz.service.index;

import com.spark.platform.admin.api.vo.IndexDataVo;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.index
 * @ClassName: IndexService
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/6/1 14:32
 * @Version: 1.0
 */
public interface IndexService {
    /**
     * 获取首页数据
     * @return
     */
    IndexDataVo getIndexData();

    /**
     * 删除缓存
     */
    void deleteCache();
}
