
package com.spark.platform.common.base.exception;

import com.spark.platform.common.base.enums.SparkHttpStatus;
import com.spark.platform.common.base.support.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.exception
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理类
 * @Version: 1.0
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务校验出错
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ApiResponse defaultErrorHandler(BusinessException e) {
        log.error("业务异常 ex={}",e.getMessage(),e);
        return new ApiResponse(SparkHttpStatus.COMMON_FAIL.getCode(),e.getMessage());
    }

    /**
     * 公共运行异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = CommonException.class)
    public ApiResponse defaultErrorHandler(CommonException e) {
        log.error("公共异常 ex={}",e.getMessage(),e);
        return new ApiResponse(SparkHttpStatus.COMMON_FAIL.getCode(),new CommonException().getMessage());
    }

    /**
     * 全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ApiResponse defaultErrorHandler(RuntimeException e) {
        log.error("全局运行异常 ex={}",e.getMessage(),e);
        return new ApiResponse(SparkHttpStatus.SERVER_FUGUE.getCode(),"不允许访问".equals(e.getMessage()) ? e.getMessage():SparkHttpStatus.SERVER_FUGUE.getMessage());
    }

    /**
     * 全部异常
     * @param e
     * @return
     */
    @ExceptionHandler( Exception.class)
    public ApiResponse defaultErrorException(Exception e){
        log.error("全局异常 ex={}",e.getMessage(),e);
        ApiResponse apiResponse = new ApiResponse(SparkHttpStatus.SERVER_FUGUE.getCode(), SparkHttpStatus.SERVER_FUGUE.getMessage());
        apiResponse.setData(e.getMessage());
        return apiResponse;
    }
    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse nullPointerExceptionHandler(NullPointerException e) {
        log.error("空指针异常 ex={}",e.getMessage(),e);
        return new ApiResponse(SparkHttpStatus.SERVER_ERROR.getCode(), SparkHttpStatus.SERVER_ERROR.getMessage());
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse classCastExceptionHandler(ClassCastException e) {
        log.error("类型转换异常 ex={}",e.getMessage(),e);
        return new ApiResponse(SparkHttpStatus.SERVER_ERROR.getCode(), SparkHttpStatus.SERVER_ERROR.getMessage());
    }
}
