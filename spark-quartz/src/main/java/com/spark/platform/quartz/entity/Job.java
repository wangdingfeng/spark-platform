package com.spark.platform.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 定时任务调度表
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SysJob对象", description="定时任务调度表")
@TableName("sys_job")
public class Job extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务名称")
    @NotNull(message = "任务名称不能为空")
    private String name;

    @ApiModelProperty(value = "任务组名")
    @NotNull(message = "任务组名不能为空")
    private String jobGroup;

    @ApiModelProperty(value = "任务类型 0 bean类型 1 rest类型 2 消息队列")
    @NotNull(message = "任务类型不能为空")
    private String type;

    @ApiModelProperty(value = "调用目标字符串")
    @NotNull(message = "调用目标字符串不能为空")
    private String invokeTarget;

    @ApiModelProperty(value = "cron执行表达式")
    @NotNull(message = "执行表达式不能为空")
    private String cronExpression;

    @ApiModelProperty(value = "计划执行错误策略（1立即执行 2执行一次 3放弃执行）")
    private String misfirePolicy;

    @ApiModelProperty(value = "是否并发执行（1允许 0禁止）")
    private String concurrent;

    @ApiModelProperty(value = "状态(1-正常，0-锁定)")
    private String status;


}
