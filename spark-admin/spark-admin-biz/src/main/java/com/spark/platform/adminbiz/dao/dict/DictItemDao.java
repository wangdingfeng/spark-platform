package com.spark.platform.adminbiz.dao.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.adminapi.entity.dict.DictItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.dao.dept
 * @ClassName: DictItemDao
 * @Author: wangdingfeng
 * @Description: 字典子表Dao
 * @Date: 2020/3/26 9:59
 * @Version: 1.0
 */
@Repository
public interface DictItemDao extends BaseMapper<DictItem> {
    /**
     * 查询所有的字典项
     * @return
     */
    List<DictItem> selectAll();
}
