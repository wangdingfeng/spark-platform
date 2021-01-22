package com.spark.platform.admin.api.entity.log;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.entity.log
 * @ClassName: ApiLog
 * @Author: wangdingfeng
 * @Description: api请求日志
 * @Date: 2020/3/24 13:09
 * @Version: 1.0
 */
@Data
@TableName("sys_log_api")
@ApiModel(value = "LogApi",description = "日志")
public class LogApi implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "方法名")
    private String method;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "访问时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "耗时")
    private Long times;

    @ApiModelProperty(value = "访问用户")
    private String creator;

    @ApiModelProperty(value = "访问ip")
    private String ip;

    @ApiModelProperty(value = "地址")
    private String location;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "错误日志")
    private String errorLog;
    /**
     * 不持久化参数
     */
    @TableField(exist = false)
    private String createTimeStr;

}
