package com.spark.platform.admin.biz.service.dept.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.spark.platform.admin.api.entity.dept.Dept;
import com.spark.platform.admin.api.vo.VueTree;
import com.spark.platform.admin.biz.dao.dept.DeptDao;
import com.spark.platform.admin.biz.service.dept.DeptService;
import com.spark.platform.common.utils.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/3/20 22:34
 * @Description: 部门管理
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

    @Override
    public List<Dept> list(Dept dept) {
        if (null != dept && StringUtils.isNotBlank(dept.getFullName())) {
            return super.list(Wrappers.<Dept>lambdaQuery().like(Dept::getFullName,dept.getFullName()));
        }

        return (List<Dept>) TreeUtils.toTree(super.list(), Dept.class);
    }

    @Override
    public List<VueTree> getTree(boolean isRoot) {
        List<VueTree> vueTrees = (List<VueTree>) TreeUtils.toTree(super.baseMapper.getTree(), VueTree.class);
        //是否拼接根节点
        if (isRoot) {
            vueTrees = Lists.newArrayList(VueTree.builder().id(0L).label("根目录").children(vueTrees).build());
        }
        return vueTrees;
    }

    @Override
    public boolean saveOrUpdate(Dept entity) {
        String pids = "0";
        if (entity.getPid().compareTo(0L) != 0) {
            //是否是根节点
            pids = super.baseMapper.getPidsById(entity.getPid()) + "," + entity.getPid();
        }
        entity.setPids(pids);
        return super.saveOrUpdate(entity);
    }
}
