package com.spark.platform.wx.shop.biz.api.controller;

import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.wx.shop.api.dto.SubmitOrderDTO;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import com.spark.platform.wx.shop.biz.api.service.ApiOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/11 16:59
 * @Version: 1.0
 */
@RestController
@Api(tags = "小程序订单中心接口")
@RequestMapping("/orderApi")
@RequiredArgsConstructor
public class ApiOrderController extends BaseController {

    private final ApiOrderService apiOrderService;

    @PostMapping
    @ApiOperation(value = "提交订单")
    public ApiResponse<ShopUser> submit(@Valid @RequestBody SubmitOrderDTO orderDTO) {
        return success(apiOrderService.submit(orderDTO));
    }
}
