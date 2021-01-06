package com.spark.platform.wx.shop.biz.marketing.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckillGoods;

/**
 * <p>
 * 商品秒杀配置列表 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-05
 */
public interface ShopSeckillGoodsService extends IService<ShopSeckillGoods> {

    /**
     * 查询商品卡辛
     * @param page
     * @param shopSeckillGoods
     * @return
     */
    IPage findPage(Page page, ShopSeckillGoods shopSeckillGoods);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteGoods(Integer id);

}
