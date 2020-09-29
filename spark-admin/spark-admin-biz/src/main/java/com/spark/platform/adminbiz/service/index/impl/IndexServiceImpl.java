package com.spark.platform.adminbiz.service.index.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.spark.platform.adminapi.vo.IndexDataVo;
import com.spark.platform.adminbiz.dao.log.LoginLogDao;
import com.spark.platform.adminbiz.service.index.IndexService;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.utils.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.index.impl
 * @ClassName: IndexServiceImpl
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/6/1 14:32
 * @Version: 1.0
 */
@Service
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final LoginLogDao loginLogDao;
    private final RedisUtils redisUtils;

    @Override
    public IndexDataVo getIndexData() {
        List<Map<String,Object>> mapList = loginLogDao.countMapData();
        IndexDataVo indexDataVo = new IndexDataVo();
        indexDataVo.setTodayIPNum(loginLogDao.countTodayIP());
        indexDataVo.setVisits(loginLogDao.selectCount(Wrappers.emptyWrapper()));
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

    @Override
    public void deleteCache() {
        //删除客户端
        Set<String> clientKeys = redisUtils.keys(GlobalsConstants.REDIS_CLIENT_CACHE+"::"+GlobalsConstants.CLIENT_DETAILS_KEY+"*");
        redisUtils.delete( clientKeys);
        //删除字典缓存
        redisUtils.delete(GlobalsConstants.REDIS_DICT_CACHE+"::"+GlobalsConstants.DICT_KEY_All_PREFIX);
        //删除用户缓存
        Set<String> userKeys = redisUtils.keys(GlobalsConstants.REDIS_USER_CACHE+"::"+GlobalsConstants.USER_KEY_PREFIX+"*");
        redisUtils.delete( userKeys);
    }
}
