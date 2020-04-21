package com.spark.platform.adminbiz.service.menu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.adminapi.entity.authority.Menu;
import com.spark.platform.adminapi.vo.MenuVue;
import com.spark.platform.adminapi.vo.VueTree;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.menu
 * @ClassName: MenuService
 * @Author: wangdingfeng
 * @Description: 菜单 Servcie
 * @Date: 2020/3/16 15:30
 * @Version: 1.0
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查询菜单树
     * @param name
     * @return
     */
    List<Menu> treeList(String name);

    /**
     * 根据账号查询菜单
     * @param userName 用户账号
     * @param filterType  过滤菜单类型
     * @return
     */
    List<Menu> findMenuByUserName(String userName, String filterType);

    /**
     * 构建菜单树
     * @param userName
     * @return
     */
    List<MenuVue> findMenuTree(String userName);

    /**
     * 构建菜单树
     * @param userId
     * @return
     */
    List<Menu> findAuthByUserId(Long userId);

    /**
     * 根据角色id 查询 角色拥有的权限树
     * @param roleId
     * @return
     */
    List<Menu> getMenuByRoleId(Long roleId);

    /**
     * 根据角色id 查询 角色拥有的权限树
     * @param roleId
     * @return
     */
    List<Menu> getMenuTreeByRoleId(Long roleId);

    /**
     * 通过角色id 获取 菜单ids
     * @param roleId
     * @return
     */
    List<Long> getMenuIdsByRole(Long roleId);

    /**
     * 查询菜单树
     * @return
     */
    List<VueTree> getMenuTree();
}
