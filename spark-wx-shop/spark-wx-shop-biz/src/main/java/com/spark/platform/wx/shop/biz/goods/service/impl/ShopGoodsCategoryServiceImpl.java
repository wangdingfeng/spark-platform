package com.spark.platform.wx.shop.biz.goods.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spark.platform.common.base.constants.RedisConstants;
import com.spark.platform.common.base.utils.RedisUtils;
import com.spark.platform.common.utils.TreeUtils;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsCategory;
import com.spark.platform.wx.shop.api.vo.GoodsCategoryVo;
import com.spark.platform.wx.shop.biz.goods.dao.ShopGoodsCategoryDao;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-17
 */
@Service
@RequiredArgsConstructor
public class ShopGoodsCategoryServiceImpl extends ServiceImpl<ShopGoodsCategoryDao, ShopGoodsCategory> implements ShopGoodsCategoryService {

    private final RedisUtils redisUtils;

    @Override
    public List<ShopGoodsCategory> tree(String name) {
        if(StringUtils.isNotBlank(name)){
            return super.list(Wrappers.<ShopGoodsCategory>lambdaQuery().like(ShopGoodsCategory::getName,name));
        }
        List<ShopGoodsCategory> categoryList;
        if(redisUtils.hasKey(RedisConstants.SHOP_CATEGORY_CACHE)){
            categoryList = JSONArray.parseArray(redisUtils.get(RedisConstants.SHOP_CATEGORY_CACHE),ShopGoodsCategory.class);
        }else {
            categoryList = (List<ShopGoodsCategory>) TreeUtils.toTree(super.list(), ShopGoodsCategory.class);
            redisUtils.set(RedisConstants.SHOP_CATEGORY_CACHE, JSONArray.toJSONString(categoryList));
        }
        return categoryList;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean deleteCategory(Integer id) {
        redisUtils.delete(RedisConstants.SHOP_CATEGORY_CACHE);
        // 校验父级元素是不是只有这一个子元素 是就更新 是否末级元素
        Integer pId = super.baseMapper.getPidById((Integer) id);
        int count = super.count(Wrappers.<ShopGoodsCategory>lambdaQuery().eq(ShopGoodsCategory::getPid,pId));
        if(count == 1){
            ShopGoodsCategory parent = new ShopGoodsCategory();
            parent.setId(pId);
            parent.setIsLeaf(true);
            super.updateById(parent);
        }
        return super.removeById(id);
    }

    @Override
    public List<GoodsCategoryVo> treeVo(Integer level) {
        return (List<GoodsCategoryVo>) TreeUtils.toTree(super.baseMapper.findVoLevel(level), GoodsCategoryVo.class);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveOrUpdate(ShopGoodsCategory entity) {
        //查询父节点信息
        ShopGoodsCategory parent = super.getOne(Wrappers.<ShopGoodsCategory>lambdaQuery().eq(ShopGoodsCategory::getId,entity.getPid()));
        if(null == parent){
            entity.setPids("0");
        }else {
            entity.setPids(parent.getPids()+ StringPool.COMMA +entity.getPid());
            entity.setLevel(parent.getLevel()+1);
            // 更新父表的 是否末级元素
            parent.setIsLeaf(false);
            super.updateById(parent);
        }
        redisUtils.delete(RedisConstants.SHOP_CATEGORY_CACHE);
        return super.saveOrUpdate(entity);
    }
}
