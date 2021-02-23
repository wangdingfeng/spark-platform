package com.spark.platform.wx.shop.biz.setting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.enums.DelFlagEnum;
import com.spark.platform.wx.shop.api.entity.setting.ShopSettingSwiper;
import com.spark.platform.wx.shop.api.vo.SwiperVo;
import com.spark.platform.wx.shop.biz.setting.dao.ShopSettingSwiperDao;
import com.spark.platform.wx.shop.biz.setting.service.ShopSettingSwiperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页轮播图 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-03
 */
@Service
public class ShopSettingSwiperServiceImpl extends ServiceImpl<ShopSettingSwiperDao, ShopSettingSwiper> implements ShopSettingSwiperService {

    @Override
    public IPage finPage(Page page, ShopSettingSwiper shopSettingSwiper) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(StringUtils.isNotBlank(shopSettingSwiper.getStatus()),"s.status",shopSettingSwiper.getStatus());
        wrapper.eq("s.del_flag", DelFlagEnum.NORMAL.getValue());
        return super.baseMapper.listPage(page, wrapper);
    }

    @Override
    public List<SwiperVo> limitSwiperVo(Integer limit) {
        return super.baseMapper.limitSwiperVo(limit);
    }
}
