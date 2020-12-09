package com.spark.platform.quartz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.quartz.entity.JobLog;
import com.spark.platform.quartz.service.JobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.spark.platform.common.base.support.BaseController;

/**
 * <p>
 * 定时任务调度日志表 前端控制器
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/job-log")
@Api(tags = "任务调度日志")
@RequiredArgsConstructor
public class JobLogController extends BaseController {

    private final JobLogService jobLogService;

    @PostMapping("/page")
    @ApiOperation(value = "分页查询")
    public ApiResponse page(JobLog jobLog, Page page){
        return success(jobLogService.findPage(jobLog,page));
    }

}
