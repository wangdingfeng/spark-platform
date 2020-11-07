package com.spark.platform.admin.biz.dao.dept;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.admin.api.entity.dept.Dept;
import com.spark.platform.admin.api.vo.VueTree;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/3/20 22:32
 * @Description: 部门Dao
 */
@Repository
public interface DeptDao extends BaseMapper<Dept> {

    /**
     * 获取菜单树
     * @return
     */
    @Select("SELECT id,pid,simple_name AS 'label' FROM sys_dept WHERE del_flag = 0")
    @ResultType(VueTree.class)
    List<VueTree> getTree();

    /**
     * 获取父节点的层级
     * @param id
     * @return
     */
    @Select("SELECT pids FROM sys_dept WHERE id=#{id}")
    String getPidsById(Long id);


}
