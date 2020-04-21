package com.spark.platform.adminapi.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.entity.user
 * @ClassName: UserRole
 * @Description:
 * @Version: 1.0
 */
@Data
@TableName("sys_user_role")
@ApiModel(value = "UserRole",description = "用户角色设置")
public class UserRole{

    /**
     * id
     * */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户id
     */
    private Long userId;

    public UserRole(){

    }

    public UserRole(Long roleId,Long userId){
        this.roleId = roleId;
        this.userId = userId;
    }
}
