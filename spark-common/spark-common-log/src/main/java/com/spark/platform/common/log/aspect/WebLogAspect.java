package com.spark.platform.common.log.aspect;

import cn.hutool.core.util.URLUtil;
import com.spark.platform.admin.api.entity.log.LogApi;
import com.spark.platform.common.base.enums.SparkHttpStatus;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.log.annotation.ApiLog;
import com.spark.platform.common.log.event.ApiLogEvent;
import com.spark.platform.common.utils.AddressUtils;
import com.spark.platform.common.base.utils.SpringContextHolder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;


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
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(public * com.spark.platform..*.controller..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        Long startTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        Integer code = SparkHttpStatus.SUCCESS.getCode();
        //获取请求码
        if(result instanceof ApiResponse){
            code = ((ApiResponse)result).getCode();
        }

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取 swagger 注解
        ApiOperation apiOperation = signature.getMethod().getAnnotation(ApiOperation.class);
        String description = null != apiOperation ? apiOperation.value() : "";
        //过滤不需要记录的日志
        ApiLog apiLogAnno = signature.getMethod().getAnnotation(ApiLog.class);
        Boolean flag = false;
        if(null != apiLogAnno){
            flag = apiLogAnno.ignore();
            description = StringUtils.isNotBlank(apiLogAnno.value()) ? apiLogAnno.value() : description;
        }
        if(!flag){
            LogApi apiLog = new LogApi();
            apiLog.setCreateTime(LocalDateTime.now());
            apiLog.setCreator(getUsername());
            apiLog.setParams(Arrays.toString(joinPoint.getArgs()));
            String className = joinPoint.getTarget().getClass().getName();
            apiLog.setMethod(className + "." + request.getMethod());
            apiLog.setUrl(URLUtil.getPath(request.getRequestURI()));
            String ip = AddressUtils.getIpAddress(request);
            apiLog.setIp(ip);
            apiLog.setDescription(description);
            apiLog.setTimes(endTime - startTime);
            apiLog.setLocation(AddressUtils.getCityInfo(ip));
            apiLog.setStatus(code);
            SpringContextHolder.publishEvent(new ApiLogEvent(apiLog));
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
