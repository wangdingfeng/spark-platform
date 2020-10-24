package com.spark.platform.adminbiz.service.role.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.spark.platform.adminapi.entity.role.Role;
import com.spark.platform.adminapi.entity.role.RoleMenu;
import com.spark.platform.adminapi.entity.user.User;
import com.spark.platform.adminbiz.dao.role.RoleDao;
import com.spark.platform.adminbiz.dao.role.RoleMenuDao;
import com.spark.platform.adminbiz.dao.user.UserDao;
import com.spark.platform.adminbiz.service.role.RoleService;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.common.base.utils.RedisUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    private final RoleDao roleDao;
    private final RoleMenuDao roleMenuDao;
    private final UserDao userDao;
    private final RedisUtils redisUtils;

    @Override
    public List<Role> getRoleByUserId(Long userId) {
        return roleDao.getRoleByUserId(userId);
    }

    @Override
    public IPage findPage(Role role, Page page) {
        QueryWrapper queryWrapper = new QueryWrapper<Role>();
        WrapperSupport.putParamsLike(queryWrapper, role, "roleName", "roleCode");
        WrapperSupport.putParamsEqual(queryWrapper, role, "deptId");
        return roleDao.selectPage(page, queryWrapper);
    }

    @Override
    public Role saveOrUpdateRole(Role role) {
        validateRoleCode(role.getRoleCode(), role.getId());
        super.saveOrUpdate(role);
        return role;
    }

    @Override
    public void saveRoleAuth(Role role) {
        Preconditions.checkArgument(null != role && null != role.getId(), "角色id不能为空");
        //删除该角色下所有的权限
        int i = roleMenuDao.delteRoleAuth(role.getId());
        log.info("删除角色：{}权限:{}个", role.getId(), i);
        for (Long menuId : role.getMenuIds()) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getId());
            roleMenu.setMenuId(menuId);
            roleMenuDao.insert(roleMenu);
        }
        //查询当前角色下所有的用户  并删除缓存
        List<User> users = userDao.findUsersByRoleId(role.getId());
        users.forEach(user -> {
            //删除缓存
            redisUtils.delete(GlobalsConstants.getCacheKey(GlobalsConstants.REDIS_USER_CACHE, GlobalsConstants.USER_KEY_PREFIX + user.getUsername()));
            redisUtils.delete(GlobalsConstants.getCacheKey(GlobalsConstants.REDIS_USER_CACHE, GlobalsConstants.USER_INFO_KEY_PREFIX + user.getUsername()));
        });
    }

    @Override
    public List<Role> findAllRole() {
        return super.baseMapper.findAllRole();
    }

    @Override
    public void validateRoleCode(String roleCode, Long roleId) {
        roleCode = roleCode.toUpperCase();
        //判断是否包含
        if (!roleCode.startsWith(GlobalsConstants.ROLE_PREFIX)) {
            throw new BusinessException("角色编号应该包含ROLE_");
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (null != roleId) {
            queryWrapper.ne("id", roleId);
        }
        queryWrapper.eq("role_code", roleCode);
        if (0 != super.count(queryWrapper)) {
            throw new BusinessException("角色编号重复");
        }
    }

    @Override
    public boolean delete(Long roleId) {
        String roleCode = super.baseMapper.getRoleCode(roleId);
        if (GlobalsConstants.ROLE_ADMIN.equals(roleCode)) {
            throw new BusinessException("超级管路员角色，不允许删除");
        }
        return super.removeById(roleId);
    }


}
