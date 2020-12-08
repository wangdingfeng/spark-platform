package com.spark.platform.admin.biz.service.index.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.spark.platform.admin.api.vo.IndexDataVo;
import com.spark.platform.admin.biz.dao.log.LoginLogDao;
import com.spark.platform.admin.biz.service.index.IndexService;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.constants.RedisConstants;
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
 * @Description: 首页信息
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
        List<Map<String, Object>> mapList = loginLogDao.countMapData();
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
        Set<String> clientKeys = redisUtils.keys(RedisConstants.CLIENT_CACHE);
        redisUtils.delete(clientKeys);
        //删除字典缓存
        redisUtils.delete(GlobalsConstants.getCacheKey(RedisConstants.DICT_CACHE, RedisConstants.DICT_KEY_ALL_PREFIX));
        //删除用户缓存
        Set<String> userKeys = redisUtils.keys(GlobalsConstants.getCacheKey(RedisConstants.USER_CACHE, RedisConstants.USER_KEY_PREFIX + "*"));
        redisUtils.delete(userKeys);
        // 删除前端获取用户详细信息 包含菜单的缓存
        Set<String> userPrincipalKeys = redisUtils.keys(GlobalsConstants.getCacheKey(RedisConstants.USER_CACHE, RedisConstants.USER_INFO_KEY_PREFIX + "*"));
        redisUtils.delete(userPrincipalKeys);
    }
}
