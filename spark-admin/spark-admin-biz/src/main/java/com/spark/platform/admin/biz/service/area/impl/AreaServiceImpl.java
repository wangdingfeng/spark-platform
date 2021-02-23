package com.spark.platform.admin.biz.service.area.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.admin.api.entity.area.Area;
import com.spark.platform.admin.biz.dao.area.AreaDao;
import com.spark.platform.admin.biz.service.area.AreaService;
import com.spark.platform.common.utils.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 行政区划 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaDao, Area> implements AreaService {

    @Override
    public List<Area> list(Area area) {
        LambdaQueryWrapper<Area> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Area::getParentId,null == area.getParentId() ? 0 : area.getParentId());
        if (StringUtils.isNotBlank(area.getAreaName())) {
            wrapper.like(Area::getAreaName, area.getAreaName());
        }
        return super.list(wrapper);
    }
}
