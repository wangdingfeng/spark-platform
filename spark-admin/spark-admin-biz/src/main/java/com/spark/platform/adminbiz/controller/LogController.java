package com.spark.platform.adminbiz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.adminapi.entity.log.LogApi;
import com.spark.platform.adminapi.entity.log.LogLogin;
import com.spark.platform.adminbiz.service.log.LogApiService;
import com.spark.platform.adminbiz.service.log.LogLoginService;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.common.log.annotation.ApiLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.controller
 * @ClassName: ApiLogController
 * @Author: wangdingfeng
 * @Description: 日志
 * @Date: 2020/3/24 13:20
 * @Version: 1.0
 */
@RestController
@Api(tags = "日志")
@AllArgsConstructor
public class LogController extends BaseController {

    private final LogApiService apiLogService;
    private final LogLoginService loginLogService;

    @PostMapping("/log/page")
    @ApiOperation(value = "分页获取日志信息")
    public ApiResponse page(LogApi apiLog, Page page) {
        return success(apiLogService.findPage(apiLog, page));
    }

    @PostMapping("/log/api")
    @ApiLog(ignore = true)
    @ApiOperation(value = "保存日志信息")
    public ApiResponse save(@RequestBody LogApi apiLog) {
        return success(apiLogService.save(apiLog));
    }

    @PostMapping("/login-log/api")
    @ApiLog(ignore = true)
    @ApiOperation(value = "保存登录日志信息")
    public ApiResponse save(@RequestBody LogLogin loginLog) {
        return success(loginLogService.save(loginLog));
    }

    @PostMapping("/login-log/page")
    @ApiOperation(value = "分页获取登录日志信息")
    public ApiResponse page(LogLogin loginLog, Page page) {
        return success(loginLogService.findPage(page, loginLog));
    }

    @GetMapping("/login-log/{username}")
    @ApiOperation(value = "获取用户最近登录日志10条")
    public ApiResponse findLately(@PathVariable String username){
        return success(loginLogService.findLately(username));
    }

}
