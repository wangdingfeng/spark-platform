package com.spark.platform.wx.shop.biz.goods.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoods;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
public interface ShopGoodsService extends IService<ShopGoods> {

    /**
     * 查询商品卡辛
     * @param page
     * @param wrapper
     * @return
     */
    IPage pageCard(Page page, Wrapper wrapper);
    /**
     * 保存商品信息
     * @param shopGoods
     */
     void saveShopGoods(ShopGoods shopGoods);
    /**
     * 更新商品信息
     * @param shopGoods
     */
    void updateShopGoods(ShopGoods shopGoods);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    ShopGoods getShopGoods(Integer id);

    /**
     * 修改商品上架状态
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Integer id, String status);

    /**
     * 计算总库存
     * @param id
     * @param num
     * @return
     */
    void calTotalStock(Integer id, Integer num);

}
