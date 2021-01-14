package com.spark.platform.wx.shop.biz.config;

import com.spark.platform.common.base.constants.GlobalsConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.config
 * @ClassName: YTOConfig
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/13 18:07
 * @Version: 1.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = GlobalsConstants.SPARK_PREFIX)
public class YTOConfig {

}
