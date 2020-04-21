package com.spark.platform.adminbiz.service.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.adminapi.entity.dict.DictItem;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.dict
 * @ClassName: DictItemService
 * @Author: wangdingfeng
 * @Description: 字典 子列表Service
 * @Date: 2020/3/26 10:17
 * @Version: 1.0
 */
public interface DictItemService extends IService<DictItem> {
    /**
     * 通过类型查询字典子项
     * @param type
     * @return
     */
    List<DictItem> findItemType(String type);

}
