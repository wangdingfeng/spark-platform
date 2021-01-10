package com.spark.platform.wx.shop.biz.marketing.service.impl;

import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckill;
import com.spark.platform.wx.shop.api.vo.SeckillVo;
import com.spark.platform.wx.shop.biz.marketing.dao.ShopSeckillDao;
import com.spark.platform.wx.shop.biz.marketing.service.ShopSeckillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 秒杀配置 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-05
 */
@Service
public class ShopSeckillServiceImpl extends ServiceImpl<ShopSeckillDao, ShopSeckill> implements ShopSeckillService {

    @Override
    public List<SeckillVo> findVoByStatus(Boolean status) {
        return super.baseMapper.findVoByStatus(status);
    }
}
