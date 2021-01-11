package com.spark.platform.wx.shop.biz.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.wx.shop.api.dto.ShopOrderQueryDTO;
import com.spark.platform.wx.shop.api.dto.SubmitOrderDTO;
import com.spark.platform.wx.shop.api.entity.user.ShopUser;
import com.spark.platform.wx.shop.api.vo.OrderCardVo;
import com.spark.platform.wx.shop.biz.api.service.ApiOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/page")
    @ApiOperation(value = "分页查询订单")
    public ApiResponse<IPage<OrderCardVo>> page(ShopOrderQueryDTO queryDTO) {
        return success(apiOrderService.page(queryDTO));
    }

    @PutMapping("/{orderId}/cancel")
    @ApiOperation(value = "取消订单")
    public ApiResponse cancel(@PathVariable Integer orderId) {
        return success(apiOrderService.cancel(orderId));
    }

    @PutMapping("/{orderId}/confirm")
    @ApiOperation(value = "确认收货")
    public ApiResponse confirmSend(@PathVariable Integer orderId) {
        return success(apiOrderService.confirmSend(orderId));
    }
}
