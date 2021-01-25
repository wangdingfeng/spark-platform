package com.spark.platform.wx.shop.biz.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.dto.UserCartDTO;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCart;

import java.util.List;

/**
 * <p>
 * 会员购物车 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
public interface ShopUserCartService extends IService<ShopUserCart> {

    /**
     * 分页
     * @param page
     * @param shopUserCart
     * @return
     */
    IPage listPage(Page page, ShopUserCart shopUserCart);

    /**
     * 根据ids查询
     * @param ids
     * @return
     */
    List<ShopUserCart> findByIds(List<Integer> ids);

    /**
     * 保存更新购物车
     * @param userCart
     * @return
     */
    boolean submitCart(UserCartDTO userCart);

    /**
     * 删除用户购物车
     * @param userId
     * @param id
     * @return
     */
    boolean deleteCart(Integer userId, Integer id);
}
