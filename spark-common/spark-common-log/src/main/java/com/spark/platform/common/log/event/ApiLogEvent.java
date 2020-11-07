package com.spark.platform.common.log.event;

import com.spark.platform.admin.api.entity.log.LogApi;
import org.springframework.context.ApplicationEvent;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.log.event
 * @ClassName: ApiLogEvent
 * @Author: wangdingfeng
 * @Description: Api日志事件
 * @Date: 2020/4/21 16:20
 * @Version: 1.0
 */
public class ApiLogEvent extends ApplicationEvent {

    public ApiLogEvent(LogApi source) {
        super(source);
    }
}
