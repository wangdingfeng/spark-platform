package com.spark.platform.adminbiz.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spark.platform.adminapi.entity.dict.Dict;
import com.spark.platform.adminapi.entity.dict.DictItem;
import com.spark.platform.adminbiz.dao.dict.DictDao;
import com.spark.platform.adminbiz.dao.dict.DictItemDao;
import com.spark.platform.adminbiz.service.dict.DictService;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.common.config.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author: wangdingfeng
 * @Date: 2020/3/21 13:36
 * @Description: 字典
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictDao, Dict> implements DictService {

    @Autowired
    private  DictItemDao dictItemDao;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public IPage findPage(Dict dict, Page page) {
        QueryWrapper wrapper = new QueryWrapper<Dict>();
        wrapper.orderByDesc("modify_date");
        WrapperSupport.putParamsLike(wrapper,dict,"name");
        WrapperSupport.putParamsEqual(wrapper,dict,"type");
        return super.page(page,wrapper);
    }

    @Override
    @Cacheable(value = GlobalsConstants.REDIS_DICT_CACHE, unless = "#result == null",key ="T(com.spark.platform.common.base.constants.GlobalsConstants).DICT_KEY_All_PREFIX")
    public Map<String, List<DictItem>> selectAllMap() {
        List<DictItem> dictItems = dictItemDao.selectAll();
        Map<String, List<DictItem>> map = Maps.newHashMap();
        dictItems.forEach(dictItem -> {
            if(map.containsKey(dictItem.getType())){
                List<DictItem> itemList =  map.get(dictItem.getType());
                itemList.add(dictItem);
                map.put(dictItem.getType(),itemList);
            }else{
                map.put(dictItem.getType(), Lists.newArrayList(dictItem));
            }
        });
        return map;
    }

    @Override
    public void resetCache() {
        //删除缓存
        redisUtils.delete(GlobalsConstants.REDIS_DICT_CACHE+"::"+GlobalsConstants.DICT_KEY_All_PREFIX);
        this.selectAllMap();
    }

}
