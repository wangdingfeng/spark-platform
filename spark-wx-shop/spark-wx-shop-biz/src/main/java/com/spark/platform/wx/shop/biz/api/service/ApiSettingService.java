package com.spark.platform.wx.shop.biz.api.service;

import com.spark.platform.wx.shop.api.vo.SwiperVo;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.api.service
 * @ClassName: ApiSettingService
 * @Author: wangdingfeng
 * @Description: 设置api Service
 * @Date: 2021/1/3 17:20
 * @Version: 1.0
 */
public interface ApiSettingService {
    /**
     * 查询轮播图 最多只能有6个
     * @return
     */
    List<SwiperVo> swiper();
}
