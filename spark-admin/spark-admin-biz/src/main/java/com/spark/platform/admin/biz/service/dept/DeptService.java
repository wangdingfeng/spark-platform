package com.spark.platform.admin.biz.service.dept;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.admin.api.entity.dept.Dept;
import com.spark.platform.admin.api.vo.VueTree;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/3/20 22:33
 * @Description: 部门 service
 */
public interface DeptService extends IService<Dept> {
    /**
     * 查询部门
     * @param dept
     * @return
     */
    List<Dept> list(Dept dept);

    /**
     * 获取树
     * @return
     */
    List<VueTree> getTree(boolean isRoot);
}
