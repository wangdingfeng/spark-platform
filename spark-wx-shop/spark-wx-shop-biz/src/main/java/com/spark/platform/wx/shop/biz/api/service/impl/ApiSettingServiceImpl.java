package com.spark.platform.wx.shop.biz.api.service.impl;

import com.spark.platform.wx.shop.api.vo.SwiperVo;
import com.spark.platform.wx.shop.biz.api.service.ApiSettingService;
import com.spark.platform.wx.shop.biz.setting.service.ShopSettingSwiperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.api.service.impl
 * @ClassName: ApiSettingServiceImpl
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/3 17:30
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class ApiSettingServiceImpl implements ApiSettingService {

    private final ShopSettingSwiperService shopSettingSwiperService;

    @Override
    public List<SwiperVo> swiper() {
        return shopSettingSwiperService.limitSwiperVo(6);
    }
}
