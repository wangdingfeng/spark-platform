package com.spark.platform.adminbiz.service.log;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.adminapi.entity.log.LogLogin;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-04-18
 */
public interface LogLoginService extends IService<LogLogin> {
    /**
     * 分页查询
     * @param page
     * @param loginLog
     * @return
     */
    IPage findPage(Page page, LogLogin loginLog);

}
