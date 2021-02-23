package com.spark.platform.wx.shop.biz.marketing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckill;
import com.spark.platform.wx.shop.api.vo.SeckillVo;

import java.util.List;

/**
 * <p>
 * 秒杀配置 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-05
 */
public interface ShopSeckillService extends IService<ShopSeckill> {
    /**
     * 根据状态查询数据vo
     * @param status 状态
     * @return
     */
    List<SeckillVo> findVoByStatus(Boolean status);

}
