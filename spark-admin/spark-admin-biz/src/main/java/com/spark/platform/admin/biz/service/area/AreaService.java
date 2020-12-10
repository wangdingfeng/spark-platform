package com.spark.platform.admin.biz.service.area;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.admin.api.entity.area.Area;

import java.util.List;

/**
 * <p>
 * 行政区划 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
public interface AreaService extends IService<Area> {
    /**
     * 查询行政区域
     * @param area
     * @return
     */
    List<Area> list(Area area);
}
