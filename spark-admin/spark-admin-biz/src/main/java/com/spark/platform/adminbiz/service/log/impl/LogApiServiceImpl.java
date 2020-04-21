package com.spark.platform.adminbiz.service.log.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.adminapi.entity.log.LogApi;
import com.spark.platform.adminbiz.dao.log.ApiLogDao;
import com.spark.platform.adminbiz.service.log.LogApiService;
import com.spark.platform.common.base.support.WrapperSupport;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.log.impl
 * @ClassName: ApiLogServiceImpl
 * @Author: wangdingfeng
 * @Description: 日志Service
 * @Date: 2020/3/24 13:18
 * @Version: 1.0
 */
@Service
public class LogApiServiceImpl extends ServiceImpl<ApiLogDao, LogApi> implements LogApiService {

    @Override
    public IPage findPage(LogApi apiLog, Page page) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("create_time");
        WrapperSupport.putParamsLike(wrapper,apiLog,"username");
        return super.page(page,wrapper);
    }
}
