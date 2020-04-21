package com.spark.platform.adminbiz.service.menu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.adminapi.entity.authority.Menu;
import com.spark.platform.adminapi.vo.MenuVue;
import com.spark.platform.adminapi.vo.VueTree;
import com.spark.platform.adminbiz.dao.menu.MenuDao;
import com.spark.platform.adminbiz.service.menu.MenuService;
import com.spark.platform.common.utils.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.menu.impl
 * @ClassName: MenuServiceImpl
 * @Author: wangdingfeng
 * @Description: 菜单Service
 * @Date: 2020/3/16 15:31
 * @Version: 1.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Override
    public List<Menu> treeList(String name) {
        QueryWrapper<Menu> menuWrapper = new QueryWrapper<>();
        menuWrapper.orderByAsc("sort");
        //如果有查询条件 不拼接树
        if(StringUtils.isNotBlank(name)){
            menuWrapper.like("name",name);
            return super.list(menuWrapper);
        }
        List<Menu> menus = super.list(menuWrapper);
        return (List<Menu>) TreeUtils.toTree(menus,Menu.class);
    }

    @Override
    public List<Menu> findMenuByUserName(String userName, String filterType) {
        return this.baseMapper.findMenuByUserName(userName,filterType);
    }

    @Override
    public List<MenuVue> findMenuTree(String userName) {
        List<MenuVue> menuTree = this.baseMapper.findMenuVueByUserName(userName);
        return (List<MenuVue>) TreeUtils.toTree(menuTree,MenuVue.class);
    }

    @Override
    public List<Menu> findAuthByUserId(Long userId) {
        return super.baseMapper.findAuthByUserId(userId);
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        return super.baseMapper.getRoleMenu(roleId);
    }

    @Override
    public List<Menu> getMenuTreeByRoleId(Long roleId) {
        return (List<Menu>)TreeUtils.toTree(this.getMenuByRoleId(roleId),Menu.class);
    }

    @Override
    public List<Long> getMenuIdsByRole(Long roleId) {
        return super.baseMapper.getMenuIdsByRole(roleId);
    }

    @Override
    public List<VueTree> getMenuTree() {
        List<VueTree> vueTrees = super.baseMapper.getMenuTree();
        return (List<VueTree>)TreeUtils.toTree(vueTrees,VueTree.class);
    }
}
