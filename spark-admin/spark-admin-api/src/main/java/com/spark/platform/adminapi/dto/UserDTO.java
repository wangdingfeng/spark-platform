package com.spark.platform.adminapi.dto;

import com.spark.platform.adminapi.vo.MenuVue;
import com.spark.platform.adminapi.vo.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.dto
 * @ClassName: UserDto
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户
     * */
    private UserVo sysUser;
    /**
     * 角色
     * */
    private List<String> roles;
    /**
     * 角色名称
     * */
    private List<String> roleNames;
    /**
     * 菜单
     * */
    private List<MenuVue> menus;
    /**
     * 权限
     * */
    private List<String> permissions;
}
