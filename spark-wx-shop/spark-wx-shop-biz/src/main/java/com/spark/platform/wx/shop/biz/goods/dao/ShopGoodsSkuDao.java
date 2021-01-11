package com.spark.platform.wx.shop.biz.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品属性搭配 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
public interface ShopGoodsSkuDao extends BaseMapper<ShopGoodsSku> {

    /**
     * 扣减库存
     * @param id
     * @param num
     * @return
     */
    @Update("update shop_goods_sku set stock = stock - #{num} where id=#{id} AND  (stock - #{num}) > 0")
    @ResultType(Integer.class)
    int subStock(@Param("id") Integer id,@Param("num") Integer num);

}
