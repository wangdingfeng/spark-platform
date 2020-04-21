package com.spark.platform.common.base.exception;


import com.spark.platform.common.base.enums.SparkHttpStatus;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.exception
 * @ClassName: CommonException
 * @Description: 自定义公共运行时异常
 * @Version: 1.0
 */
public class CommonException extends RuntimeException {

    public Integer code;

    public String msg;

    public CommonException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CommonException(String msg) {
        super(msg);
        this.code = SparkHttpStatus.COMMON_FAIL.getCode();
        this.msg = msg;
    }

    public CommonException() {
        super(SparkHttpStatus.COMMON_FAIL.getMessage());
        this.code = SparkHttpStatus.COMMON_FAIL.getCode();
        this.msg = SparkHttpStatus.COMMON_FAIL.getMessage();
    }
    public CommonException(SparkHttpStatus status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.msg = status.getMessage();
    }

    public CommonException(Throwable cause) {
        super(cause);
    }


}
