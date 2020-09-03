package com.spark.platform.quartz.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.quartz.entity.Job;
import com.spark.platform.quartz.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.spark.platform.common.base.support.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 定时任务调度表 前端控制器
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/job")
@Api(tags = "任务调度")
@AllArgsConstructor
public class JobController extends BaseController {

    private final JobService jobService;

    @GetMapping
    @ApiOperation(value = "获取单个信息")
    public ApiResponse get(@PathVariable Long id){
        return success(jobService.getById(id));
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询")
    public ApiResponse page(Job job, Page page){
        return success(jobService.findPage(job,page));
    }

    @PostMapping
    @ApiOperation(value = "保存信息")
    @PreAuthorize("hasAnyAuthority('quartz:add')")
    public ApiResponse save(@RequestBody @Valid Job job){
        return success(jobService.saveJob(job));
    }

    @PutMapping
    @ApiOperation(value = "更新信息")
    @PreAuthorize("hasAnyAuthority('quartz:edit')")
    public ApiResponse update(@RequestBody @Valid Job job){
        return success(jobService.updateJob(job));
    }

    @PatchMapping("/status/{id}/{status}")
    @ApiOperation(value = "更新状态",notes = "status：0 暂停 1开始")
    @PreAuthorize("hasAnyAuthority('quartz:edit')")
    public ApiResponse changeJob(@PathVariable("id") Long id,@PathVariable("status")String status){
        return success(jobService.changeStatus(id,status));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除任务")
    @PreAuthorize("hasAnyAuthority('quartz:delete')")
    public ApiResponse delete(@PathVariable Long id){
        return success(jobService.deleteJob(id));
    }

    @GetMapping("/run/{id}")
    @ApiOperation(value = "立即运行")
    @PreAuthorize("hasAnyAuthority('quartz:edit')")
    public ApiResponse run(@PathVariable Long id){
        jobService.run(id);
        return success("运行成功");
    }



}
