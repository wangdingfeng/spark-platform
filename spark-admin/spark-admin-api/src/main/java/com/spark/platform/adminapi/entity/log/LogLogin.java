package com.spark.platform.adminapi.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录日志
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-04-18
 */
@Data
@Accessors(chain = true)
@TableName("sys_log_login")
@ApiModel(value="LogLogin对象", description="登录日志")
public class LogLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登录用户账号")
    private String username;

    @ApiModelProperty(value = "登录系统")
    private String system;

    @ApiModelProperty(value = "登录浏览器")
    private String browser;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "登录地点")
    private String location;

    @ApiModelProperty(value = "登录ip")
    private String locationIp;

    public LogLogin(){

    }

    public LogLogin(String username, String system, String browser, LocalDateTime loginTime, String location, String locationIp){
        this.username = username;
        this.system = system;
        this.browser = browser;
        this.loginTime = loginTime;
        this.location = location;
        this.locationIp = locationIp;
    }


}
