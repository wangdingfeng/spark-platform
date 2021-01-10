package com.spark.platform.wx.shop.biz.user.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 会员购物车 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
public interface ShopUserCartDao extends BaseMapper<ShopUserCart> {

    /**
     * 分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage listPage(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 查询相同的购物车商品
     * @param userId 用户
     * @param goodsId 商品
     * @param attrValIds 规格
     * @return
     */
    @Select("SELECT id FROM shop_user_cart WHERE del_flag='0' AND user_id=#{userId} AND goods_id=#{goodsId} AND attr_val_ids=#{attrValIds}")
    @ResultType(Integer.class)
    Integer findSameId(@Param("userId") Integer userId,@Param("goodsId") Integer goodsId,@Param("attrValIds") String attrValIds);

    /**
     * 更新数量
     * @param id
     * @return
     */
    @Update("UPDATE shop_user_cart set num = num+1 WHERE id=#{id}")
    int updateNum(@Param("id") Integer id);

}
