package com.spark.platform.wx.shop.biz.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsComment;
import com.spark.platform.wx.shop.biz.goods.dao.ShopGoodsCommentDao;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
@Service
public class ShopGoodCommentsServiceImpl extends ServiceImpl<ShopGoodsCommentDao, ShopGoodsComment> implements ShopGoodsCommentService {

    @Override
    public IPage listPage(Page page, ShopGoodsComment shopGoodsComment) {
        QueryWrapper queryWrapper = new QueryWrapper<ShopGoodsComment>();
        WrapperSupport.putParamsLike(queryWrapper,"g",shopGoodsComment,"goodsSn");
        WrapperSupport.putParamsLike(queryWrapper,"u",shopGoodsComment,"userName");
        queryWrapper.orderByDesc("create_date");
        return super.baseMapper.listPage(page,queryWrapper);
    }
}
