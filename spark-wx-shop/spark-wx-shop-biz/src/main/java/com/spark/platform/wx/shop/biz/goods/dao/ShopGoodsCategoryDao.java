package com.spark.platform.wx.shop.biz.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsCategory;
import com.spark.platform.wx.shop.api.vo.GoodsCategoryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
public interface ShopGoodsCategoryDao extends BaseMapper<ShopGoodsCategory> {
    /**
     * 查询父ID
     * @param id
     * @return
     */
    @Select("SELECT pid FROM shop_goods_category WHERE id=#{id}")
    Integer getPidById(@Param("id") Integer id);

    /**
     * 查询 最大层级的数据
     * @param level 最大层级
     * @return
     */
    @Select("select id,pid,pids,name 'text',pic,is_leaf from shop_goods_category where del_flag=0 AND level <= #{level}")
    List<GoodsCategoryVo> findVoLevel(@Param("level") Integer level);

}
