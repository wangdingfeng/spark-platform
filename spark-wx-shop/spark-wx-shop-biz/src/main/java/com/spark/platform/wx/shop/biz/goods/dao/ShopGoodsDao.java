package com.spark.platform.wx.shop.biz.goods.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
public interface ShopGoodsDao extends BaseMapper<ShopGoods> {
    /**
     * 分页查询商品卡片信息
     * @param page
     * @param wrapper
     * @return
     */
    IPage pageCard(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 计算总库存
     * @param id
     * @return
     */
    @Update("UPDATE shop_goods set stock = (SELECT sum(stock) FROM shop_goods_sku WHERE goods_id = #{id}) WHERE id=#{id}")
    @ResultType(Integer.class)
    int calTotalStock(@Param("id") Integer id);

}
