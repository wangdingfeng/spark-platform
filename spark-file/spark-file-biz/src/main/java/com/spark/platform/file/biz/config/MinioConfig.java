package com.spark.platform.file.biz.config;

import com.spark.platform.common.base.constants.GlobalsConstants;
import io.minio.MinioClient;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.file.biz.config
 * @ClassName: MinioConfig
 * @Author: wangdingfeng
 * @Description: Minio配置
 * @Date: 2020/11/6 13:15
 * @Version: 1.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = GlobalsConstants.MINIO_PREFIX)
public class MinioConfig {
    @ApiModelProperty("endPoint是一个URL，域名，IPv4或者IPv6地址")
    private String endpoint;
    @ApiModelProperty("TCP/IP端口号")
    private Integer port;
    @ApiModelProperty("accessKey类似于用户ID，用于唯一标识你的账户")
    private String accessKey;
    @ApiModelProperty("secretKey是你账户的密码")
    private String secretKey;
    @ApiModelProperty("如果是true，则用的是https而不是http,默认值是true")
    private Boolean secure;
    @ApiModelProperty("默认存储桶")
    private String bucketName;

    @Bean
    @SneakyThrows
    public MinioClient getMinioClient() {
        MinioClient minioClient;
        if(null == port){
            // 如果不启动端口
            minioClient = new MinioClient(endpoint, accessKey, secretKey,secure);
        }else {
            minioClient = new MinioClient(endpoint, port, accessKey, secretKey,secure);
        }
        return minioClient;
    }
}
