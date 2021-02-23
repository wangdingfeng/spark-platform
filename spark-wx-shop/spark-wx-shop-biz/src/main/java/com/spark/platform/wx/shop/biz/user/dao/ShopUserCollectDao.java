package com.spark.platform.wx.shop.biz.user.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.user.ShopUserCollect;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户收藏 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
public interface ShopUserCollectDao extends BaseMapper<ShopUserCollect> {

    /**
     * 分页
     * @param page
     * @param wrapper
     * @return
     */
    IPage listPage(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
