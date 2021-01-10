package com.spark.platform.wx.shop.biz.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsComment;
import com.spark.platform.wx.shop.biz.goods.dao.ShopGoodsCommentDao;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
        queryWrapper.eq(null != shopGoodsComment.getGoodsId(),"g.id",shopGoodsComment.getGoodsId());
        queryWrapper.like(StringUtils.isNotBlank(shopGoodsComment.getGoodsSn()),"g.goods_sn",shopGoodsComment.getGoodsSn());
        queryWrapper.like(StringUtils.isNotBlank(shopGoodsComment.getGoodsSn()),"u.user_name",shopGoodsComment.getUserName());
        queryWrapper.orderByDesc("create_date");
        return super.baseMapper.listPage(page,queryWrapper);
    }

    @Override
    public List<ShopGoodsComment> findByGoods(Integer goodsId) {
        return super.list(Wrappers.<ShopGoodsComment>lambdaQuery().eq(ShopGoodsComment::getGoodsId,goodsId));
    }
}
