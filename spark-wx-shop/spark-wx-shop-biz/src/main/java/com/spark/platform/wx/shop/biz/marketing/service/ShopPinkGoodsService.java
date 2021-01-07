package com.spark.platform.wx.shop.biz.marketing.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.marketing.ShopPinkGoods;

/**
 * <p>
 * 拼团产品 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-07
 */
public interface ShopPinkGoodsService extends IService<ShopPinkGoods> {


    /**
     * 查询商品卡辛
     * @param page
     * @param shopPinkGoods
     * @return
     */
    IPage findPage(Page page, ShopPinkGoods shopPinkGoods);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteGoods(Integer id);
}
