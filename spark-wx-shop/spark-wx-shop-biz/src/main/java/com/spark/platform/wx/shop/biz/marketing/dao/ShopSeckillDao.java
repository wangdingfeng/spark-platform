package com.spark.platform.wx.shop.biz.marketing.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckill;
import com.spark.platform.wx.shop.api.vo.SeckillVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 秒杀配置 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2021-01-05
 */
public interface ShopSeckillDao extends BaseMapper<ShopSeckill> {
    /**
     * 根据状态查询数据vo
     * @param status 状态
     * @return
     */
    @Select("SELECT * FROM shop_seckill WHERE del_flag=0 AND status=#{status}")
    @ResultType(SeckillVo.class)
    List<SeckillVo> findVoByStatus(@Param("status") Boolean status);
}
