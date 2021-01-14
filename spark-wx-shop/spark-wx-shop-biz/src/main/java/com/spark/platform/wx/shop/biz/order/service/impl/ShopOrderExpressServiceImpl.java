package com.spark.platform.wx.shop.biz.order.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.common.model.LogisticsResponse;
import com.spark.platform.common.model.WaybillNoModel;
import com.spark.platform.common.service.LogisticsService;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderExpress;
import com.spark.platform.wx.shop.biz.order.dao.ShopOrderExpressDao;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单物流信息表，发货时生成 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
@Service
@RequiredArgsConstructor
public class ShopOrderExpressServiceImpl extends ServiceImpl<ShopOrderExpressDao, ShopOrderExpress> implements ShopOrderExpressService {

    private final LogisticsService logisticsService;

    @Override
    public List<ShopOrderExpress> findOrderId(Integer orderId) {
        return super.list(Wrappers.<ShopOrderExpress>lambdaQuery().eq(ShopOrderExpress::getOrderId,orderId));
    }

    @Override
    public void saveSendData(ShopOrderExpress orderExpress) {
        // 查询物流轨迹
        this.getLastTrace(orderExpress);
        super.save(orderExpress);
    }

    @Override
    public ShopOrderExpress refresh(Integer id) {
        ShopOrderExpress orderExpress = super.getById(id);
        this.getLastTrace(orderExpress);
        super.updateById(orderExpress);
        return orderExpress;
    }

    @Override
    public List<WaybillNoModel> findTraces(Integer id) {
        // 查询物流轨迹
        ShopOrderExpress orderExpress = super.getById(id);
        LogisticsResponse<List<WaybillNoModel>> response = logisticsService.findWaybillNo(orderExpress.getShipperCode(),orderExpress.getLogisticCode());
        if(response.isSuccess()){
            return response.getObject();
        }
        return new ArrayList<>(0);
    }

    /**
     * 查询物流轨迹
     * @param orderExpress
     */
    private void getLastTrace(ShopOrderExpress orderExpress){
        // 查询物流轨迹
        LogisticsResponse<List<WaybillNoModel>> response = logisticsService.findWaybillNo(orderExpress.getShipperCode(),orderExpress.getLogisticCode());
        if(response.isSuccess()){
            if(CollUtil.isNotEmpty(response.getObject())){
                // 获取最后一条轨迹
                WaybillNoModel waybillNoModel = response.getObject().get(response.getObject().size()-1);
                orderExpress.setTraces(waybillNoModel.getStatus());
            }
        }
    }
}
