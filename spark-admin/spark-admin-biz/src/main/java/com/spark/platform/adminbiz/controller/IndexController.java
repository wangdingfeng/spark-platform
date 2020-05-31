package com.spark.platform.adminbiz.controller;

import com.spark.platform.adminbiz.service.log.LogLoginService;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/31 10:41
 * @Description:
 */
@RestController
@Api(tags = "首页信息")
@AllArgsConstructor
public class IndexController extends BaseController {

    private final LogLoginService loginLogService;

    @GetMapping("/getData")
    @ApiOperation(value = "首页信息统计")
    public ApiResponse getData(){
        return success(loginLogService.getIndexData());
    }

}
