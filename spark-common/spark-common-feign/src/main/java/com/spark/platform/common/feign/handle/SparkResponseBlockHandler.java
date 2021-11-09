package com.spark.platform.common.feign.handle;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2021/11/9 14:21
 * @Description: sentinel统一降级限流策略
 */
@Slf4j
public class SparkResponseBlockHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, BlockException e) throws Exception {
        log.error("sentinel 降级 资源名称{}", e.getRule().getResource(), e);
        Map<String,Object> map = new HashMap<>(3);
        map.put("code",504);
        map.put("msg",e.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.getWriter().print(JSON.toJSONString(map));
    }
}
