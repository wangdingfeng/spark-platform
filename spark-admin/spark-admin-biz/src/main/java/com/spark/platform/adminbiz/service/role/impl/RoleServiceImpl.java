package com.spark.platform.adminbiz.service.role.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.spark.platform.adminapi.entity.role.Role;
import com.spark.platform.adminapi.entity.role.RoleMenu;
import com.spark.platform.adminbiz.dao.role.RoleDao;
import com.spark.platform.adminbiz.dao.role.RoleMenuDao;
import com.spark.platform.adminbiz.service.role.RoleService;
import com.spark.platform.common.base.support.WrapperSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.role.impl
 * @ClassName: RoleServiceImpl
 * @Date: 2019/11/5 09:28
 * @Description:
 * @Version: 1.0
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public List<Role> getRoleByUserId(Long userId) {
        return roleDao.getRoleByUserId(userId);
    }

    @Override
    public IPage findPage(Role role, Page page) {
        QueryWrapper queryWrapper = new QueryWrapper<Role>();
        WrapperSupport.putParamsLike(queryWrapper,role,"roleName");
        return roleDao.selectPage(page,queryWrapper);
    }

    @Override
    public void saveRoleAuth(Role role) {
        Preconditions.checkArgument(null != role && null != role.getId(),"角色id不能为空");
        //删除该角色下所有的权限
        int i = roleMenuDao.delteRoleAuth(role.getId());
        log.info("删除角色：{}权限:{}个",role.getId(),i);
        for(Long menuId : role.getMenuIds()){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getId());
            roleMenu.setMenuId(menuId);
            roleMenuDao.insert(roleMenu);
        }

    }

    @Override
    public List<Role> findAllRole() {
        return super.baseMapper.findAllRole();
    }
}
