package com.spark.platform.wx.shop.biz.api.controller;

import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.wx.shop.api.dto.WxLoginDTO;
import com.spark.platform.wx.shop.biz.api.service.ApiUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.api.controller
 * @ClassName: ApiUserController
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/8 17:26
 * @Version: 1.0
 */
@RestController
@RequestMapping("/userApi")
@Api(tags = "小程序用户接口")
@RequiredArgsConstructor
public class ApiUserController extends BaseController {

    private final ApiUserService apiUserService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ApiResponse login(@Valid @RequestBody WxLoginDTO loginDTO){
        return success(apiUserService.login(loginDTO));
    }
}
