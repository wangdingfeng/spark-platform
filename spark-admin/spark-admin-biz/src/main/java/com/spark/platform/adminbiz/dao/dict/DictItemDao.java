package com.spark.platform.adminbiz.dao.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.admin.api.entity.dict.DictItem;
import com.spark.platform.common.base.vo.DictVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.dao.dept
 * @ClassName: DictItemDao
 * @Author: wangdingfeng
 * @Description: 字典子表Dao
 * @Date: 2020/3/26 9:59
 * @Version: 1.0
 */
@Repository
public interface DictItemDao extends BaseMapper<DictItem> {
    /**
     * 查询所有的字典项
     * @return
     */
    List<DictVo> selectAll();

    /**
     * 通过 父id 更新type
     * @param type
     * @param pid 父id
     */
    @Update("UPDATE sys_dict_item SET type=#{type} WHERE pid = #{pid}")
    void updateType(@Param("type")String type,@Param("pid") Long pid);
}
