package com.spark.platform.adminbiz.dao.menu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.adminapi.entity.authority.Menu;
import com.spark.platform.adminapi.vo.MenuVue;
import com.spark.platform.adminapi.vo.VueTree;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.dao.menu
 * @ClassName: MenuDao
 * @Author: wangdingfeng
 * @Description: 菜单Dao
 * @Date: 2020/3/16 15:32
 * @Version: 1.0
 */
@Repository
public interface MenuDao extends BaseMapper<Menu> {

    /**
     * 根据账号查询菜单
     * @param userName 用户账号
     * @param filterType  过滤菜单类型
     * @return
     */
    List<Menu> findMenuByUserName(@Param("userName") String userName,@Param("filterType") String filterType);

    /**
     * 根据账号查询菜单 vue
     * @param userName
     * @return
     */
    List<MenuVue> findMenuVueByUserName(@Param("userName")String userName);

    /**
     * 根据账号查询菜单 vue
     * @param userId
     * @return
     */
    List<Menu> findAuthByUserId(@Param("userId")Long userId);

    /**
     * 根据角色查询菜单
     * @param roleId
     * @return
     */
    List<Menu> getRoleMenu(@Param("roleId")Long roleId);

    /**
     * 获取菜单id
     * @param roleId
     * @return
     */
    List<Long> getMenuIdsByRole(@Param("roleId")Long roleId);

    /**
     * 查询菜单树
     * @return
     */
    @Select("SELECT id,name 'label',pid FROM sys_menu WHERE del_flag='0';")
    @ResultType(VueTree.class)
    List<VueTree> getMenuTree();
}
