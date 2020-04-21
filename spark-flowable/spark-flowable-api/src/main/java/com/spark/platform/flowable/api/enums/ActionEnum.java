package com.spark.platform.flowable.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/5 20:33
 * @Description: 任务类型
 */
@Getter
@AllArgsConstructor
public enum ActionEnum {

    COMPLETE("complete","完成任务成功"),
    CLAIM("claim","任务签收成功"),
    UNCLAIM("unclaim","任务反签收成功"),
    DELEGATE("delegate","任务委派成功"),
    RESOLVE("resolve"," 任务签收完成,返回任务人完成"),
    ASSIGNEE("assignee","任务转办成功"),
    SUSPEND("suspend","挂起流程成功"),
    ACTIVATE("activate","激活流程成功");


    private String action;
    private String name;


    public static ActionEnum actionOf(String action) {
        for(ActionEnum actionEnum : values()){
            if(actionEnum.getAction().equals(action)){
                return actionEnum;
            }
        }
        throw new RuntimeException("没有找到对应的枚举");
    }
}
