package com.spark.platform.adminapi.dto;

import com.spark.platform.adminapi.vo.MenuVue;
import com.spark.platform.adminapi.vo.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "UserDTO",description = "用户集合")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户")
    private UserVo sysUser;

    @ApiModelProperty(value = "角色")
    private List<String> roles;

    @ApiModelProperty(value = "角色名称")
    private List<String> roleNames;

    @ApiModelProperty(value = "菜单")
    private List<MenuVue> menus;

    @ApiModelProperty(value = "权限")
    private List<String> permissions;
}
