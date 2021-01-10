package com.spark.platform.wx.shop.biz.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spark.platform.wx.shop.api.dto.ShopGoodsQueryDTO;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsComment;
import com.spark.platform.wx.shop.api.vo.GoodsCardVo;
import com.spark.platform.wx.shop.api.vo.GoodsCategoryVo;
import com.spark.platform.wx.shop.api.vo.GoodsDetailVo;

import java.util.List;


/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.api.service
 * @ClassName: ApiGoodsService
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/12/23 11:53
 * @Version: 1.0
 */
public interface ApiGoodsService {
    /**
     * 查询 商品查询
     * @param shopGoodsQueryDTO
     * @return
     */
    IPage list(ShopGoodsQueryDTO shopGoodsQueryDTO);

    /**
     * 商品详情页面“大家都在看”推荐商品
     * @param goodsId
     * @return
     */
    List<GoodsCardVo> related(Integer goodsId);

    /**
     * 商品详情
     * @param userId
     * @param goodsId
     * @return
     */
    GoodsDetailVo detail(Integer userId, Integer goodsId);

    /**
     * 查询分类树
     * @param level 最大层级
     * @return
     */
    List<GoodsCategoryVo> categoryTree(Integer level);

    /**
     * 查询商品的评论信息
     * @param size
     * @param current
     * @param goodsId
     * @return
     */
    IPage<ShopGoodsComment> pageGoodsComment(Long size, Long current, Integer goodsId);

}
