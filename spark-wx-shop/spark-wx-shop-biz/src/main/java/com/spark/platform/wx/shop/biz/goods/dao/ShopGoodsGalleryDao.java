package com.spark.platform.wx.shop.biz.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsGallery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品主图 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-15
 */
public interface ShopGoodsGalleryDao extends BaseMapper<ShopGoodsGallery> {
    /**
     * 根据商品ID查询所有的ID
     * @param goodsId
     * @return
     */
    @Select(" SELECT id FROM shop_goods_gallery WHERE goods_id=#{goodsId} AND del_flag='0'")
    List<Integer> getIdsByGoods(@Param("goodsId") Integer goodsId);

}
