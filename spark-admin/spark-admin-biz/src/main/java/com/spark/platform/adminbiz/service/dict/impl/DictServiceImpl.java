package com.spark.platform.adminbiz.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.adminapi.entity.dict.Dict;
import com.spark.platform.adminbiz.dao.dict.DictDao;
import com.spark.platform.adminbiz.service.dict.DictService;
import com.spark.platform.common.base.support.WrapperSupport;
import org.springframework.stereotype.Service;


/**
 * @author: wangdingfeng
 * @Date: 2020/3/21 13:36
 * @Description: 字典
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictDao, Dict> implements DictService {


    @Override
    public IPage findPage(Dict dict, Page page) {
        QueryWrapper wrapper = new QueryWrapper<Dict>();
        wrapper.orderByDesc("modify_date");
        WrapperSupport.putParamsLike(wrapper,dict,"name");
        WrapperSupport.putParamsEqual(wrapper,dict,"type");
        return super.page(page,wrapper);
    }

}
