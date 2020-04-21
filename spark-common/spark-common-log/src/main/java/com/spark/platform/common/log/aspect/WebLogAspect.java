package com.spark.platform.common.log.aspect;

import cn.hutool.core.util.URLUtil;
import com.google.common.collect.Lists;
import com.spark.platform.adminapi.entity.log.LogApi;
import com.spark.platform.adminapi.feign.client.LogClient;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.utils.AddressUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * @ProjectName: spark-platform
 * @Package: com.spatk.platform.common.log.aspect
 * @ClassName: LogAspect
 * @Author: wangdingfeng
 * @Description: 日志切面
 * @Date: 2020/3/24 12:59
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Autowired
    private LogClient apiLogClient;

    @Pointcut("execution(public * com.spark.platform..*.controller..*.*(..))")
    public void webLog() {
    }


    @Around("webLog()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        Long startTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        Integer code = 200;
        //获取请求码
        if(result instanceof ApiResponse){
            code = ((ApiResponse)result).getCode();
        }
        //过滤不需要记录的日志
        List<String> filter = Lists.newArrayList("/log/api","/login-log/api","/authority/api/info","/user/api","/menu/api/auth");
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取 swagger 注解
        ApiOperation apiOperation = signature.getMethod().getAnnotation(ApiOperation.class);
        if(!filter.contains(request.getRequestURI())){
            LogApi apiLog = new LogApi();
            apiLog.setCreateTime(LocalDateTime.now());
            apiLog.setCreator(getUsername());
            apiLog.setParams(Arrays.toString(joinPoint.getArgs()));
            String className = joinPoint.getTarget().getClass().getName();
            apiLog.setMethod(className + "." + request.getMethod());
            apiLog.setUrl(URLUtil.getPath(request.getRequestURI()));
            String ip = AddressUtils.getIpAddress(request);
            apiLog.setIp(ip);
            apiLog.setDescription(apiOperation.value());
            apiLog.setTimes(endTime - startTime);
            apiLog.setAddress(AddressUtils.getCityInfo(ip));
            apiLog.setStatus(code);
            apiLogClient.save(apiLog);
        }
        return result;
    }

    /**
     * 获取用户名称
     *
     * @return username
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }


}
