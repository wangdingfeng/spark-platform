package com.spark.platform.wx.shop.biz.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsCategory;
import com.spark.platform.wx.shop.api.vo.GoodsCategoryVo;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
public interface ShopGoodsCategoryService extends IService<ShopGoodsCategory> {
    /**
     * 获取树
     * @return
     */
    List<ShopGoodsCategory> tree(String name);

    /**
     * 删除分类
     * @param id
     * @return
     */
    boolean deleteCategory(Integer id);

    /**
     * 查询树
     * @param level 最大层级
     * @return
     */
    List<GoodsCategoryVo> treeVo(Integer level);
}
