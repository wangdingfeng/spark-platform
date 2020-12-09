package com.spark.platform.common.log.listener;


import com.spark.platform.admin.api.entity.log.LogApi;
import com.spark.platform.admin.api.feign.client.LogClient;
import com.spark.platform.common.log.event.ApiLogEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.log.listener
 * @ClassName: ApiLogListener
 * @Author: wangdingfeng
 * @Description: api日志 监听
 * @Date: 2020/4/21 16:22
 * @Version: 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class ApiLogListener {

    private final LogClient logClient;

    @Async
    @Order
    @EventListener(ApiLogEvent.class)
    public void saveApiLog(ApiLogEvent apiLogEvent){
        LogApi logApi = (LogApi) apiLogEvent.getSource();
        logClient.save(logApi);
    }
}
