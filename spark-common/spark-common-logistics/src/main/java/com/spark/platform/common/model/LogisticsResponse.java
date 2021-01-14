package com.spark.platform.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.model
 * @ClassName: LogisticsResponse
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/14 9:29
 * @Version: 1.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsResponse<T> {
    /**&
     * 请求值
     */
    private Integer code;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 消息
     */
    private String message;
    /**
     * 内容
     */
    private T Object;
}
