package com.spark.platform.adminbiz.service.dept.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.spark.platform.adminapi.entity.dept.Dept;
import com.spark.platform.adminapi.vo.VueTree;
import com.spark.platform.adminbiz.dao.dept.DeptDao;
import com.spark.platform.adminbiz.service.dept.DeptService;
import com.spark.platform.common.utils.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/3/20 22:34
 * @Description:
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao,Dept> implements DeptService {


    @Override
    public List<Dept> list(Dept dept) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        if(null != dept && StringUtils.isNotBlank(dept.getFullName())){
            queryWrapper.like("full_name",dept.getFullName());
            return super.baseMapper.selectList(queryWrapper);
        }

        return (List<Dept>) TreeUtils.toTree(super.baseMapper.selectList(queryWrapper),Dept.class);
    }

    @Override
    public List<VueTree> getTree(boolean isRoot) {
        List<VueTree> vueTrees = (List<VueTree>)TreeUtils.toTree(super.baseMapper.getTree(),VueTree.class);
        //是否拼接根节点
        if(isRoot){
            vueTrees = Lists.newArrayList(VueTree.builder().id(0L).label("根目录").children(vueTrees).build());
        }
        return vueTrees;
    }
}
