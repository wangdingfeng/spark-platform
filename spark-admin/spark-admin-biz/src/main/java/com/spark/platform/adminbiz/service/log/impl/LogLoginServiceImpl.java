package com.spark.platform.adminbiz.service.log.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.spark.platform.adminapi.entity.log.LogLogin;
import com.spark.platform.adminapi.vo.IndexDataVo;
import com.spark.platform.adminbiz.dao.log.LoginLogDao;
import com.spark.platform.adminbiz.service.log.LogLoginService;
import com.spark.platform.common.base.support.WrapperSupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-04-18
 */
@Service
public class LogLoginServiceImpl extends ServiceImpl<LoginLogDao, LogLogin> implements LogLoginService {

    @Override
    public IPage findPage(Page page, LogLogin loginLog) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("login_time");
        WrapperSupport.putParamsLike(wrapper,loginLog,"username");
        return super.page(page, wrapper);
    }

    @Override
    public List<LogLogin> findLately(String username) {
        return super.baseMapper.findLately(username);
    }

    @Override
    public IndexDataVo getIndexData() {
        List<Map<String,Object>> mapList = super.baseMapper.countMapData();
        IndexDataVo indexDataVo = new IndexDataVo();
        indexDataVo.setTodayIPNum(super.baseMapper.countTodayIP());
        indexDataVo.setVisits(super.count());
        IndexDataVo.VisitsVo visitsVo = new IndexDataVo.VisitsVo();
        List<String> dates = Lists.newArrayList();
        List<Integer> myData = Lists.newArrayList();
        List<Integer> allData = Lists.newArrayList();
        mapList.forEach(stringObjectMap -> {
            dates.add(stringObjectMap.get("time").toString());
            myData.add(Integer.parseInt(stringObjectMap.get("myData").toString()));
            allData.add(Integer.parseInt(stringObjectMap.get("allData").toString()));
        });
        visitsVo.setDates(dates);
        visitsVo.setMyData(myData);
        visitsVo.setAllData(allData);
        indexDataVo.setVisitsVo(visitsVo);
        return indexDataVo;
    }
}
