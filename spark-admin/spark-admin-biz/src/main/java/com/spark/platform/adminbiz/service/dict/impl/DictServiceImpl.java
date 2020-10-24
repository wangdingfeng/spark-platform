package com.spark.platform.adminbiz.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spark.platform.admin.api.entity.dict.Dict;
import com.spark.platform.adminbiz.dao.dict.DictDao;
import com.spark.platform.adminbiz.dao.dict.DictItemDao;
import com.spark.platform.adminbiz.service.dict.DictService;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.common.base.utils.RedisUtils;
import com.spark.platform.common.base.vo.DictVo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @author: wangdingfeng
 * @Date: 2020/3/21 13:36
 * @Description: 字典
 */
@Service
@AllArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictDao, Dict> implements DictService {

    private final DictItemDao dictItemDao;
    private final RedisUtils redisUtils;

    @Override
    public IPage findPage(Dict dict, Page page) {
        QueryWrapper wrapper = new QueryWrapper<Dict>();
        wrapper.orderByDesc("modify_date");
        WrapperSupport.putParamsLike(wrapper,dict,"name");
        WrapperSupport.putParamsEqual(wrapper,dict,"type");
        return super.page(page,wrapper);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updateDict(Dict dict) {
        //查询类型是否修改
        Dict source = super.getById(dict.getId());
        if(!dict.getType().equals(source.getType())){
            //修改子项的type
            dictItemDao.updateType(dict.getType(),dict.getId());
        }
        return super.updateById(dict);
    }

    @Override
    @Cacheable(value = GlobalsConstants.REDIS_DICT_CACHE, unless = "#result == null",key ="T(com.spark.platform.common.base.constants.GlobalsConstants).DICT_KEY_ALL_PREFIX")
    public Map<String, List<DictVo>> selectAllMap() {
        List<DictVo> dictItems = dictItemDao.selectAll();
        Map<String, List<DictVo>> map = Maps.newHashMap();
        dictItems.forEach(dictItem -> {
            if(map.containsKey(dictItem.getType())){
                List<DictVo> itemList =  map.get(dictItem.getType());
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
        redisUtils.delete(GlobalsConstants.REDIS_DICT_CACHE+"::"+GlobalsConstants.DICT_KEY_ALL_PREFIX);
    }

}
