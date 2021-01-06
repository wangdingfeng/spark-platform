package com.spark.platform.wx.shop.biz.marketing.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckillGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品秒杀配置列表 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-05
 */
public interface ShopSeckillGoodsDao extends BaseMapper<ShopSeckillGoods> {

    /**
     * 分页查询商品卡片信息
     * @param page
     * @param wrapper
     * @return
     */
    IPage listPage(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 更新产品的活动状态为 正常
     * @param id
     */
    @Update("UPDATE shop_goods g JOIN shop_seckill_goods s ON g.id=s.goods_id set g.activity='0' WHERE s.id=#{id}")
    void updateGoodsActivity(@Param("id") Integer id);

}
