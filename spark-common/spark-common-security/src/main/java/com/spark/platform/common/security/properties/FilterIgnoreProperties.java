package com.spark.platform.common.security.properties;

import com.spark.platform.common.base.constants.GlobalsConstants;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.properties
 * @ClassName: FilterIgnoreProperties
 * @Description: 放行参数配置
 * @Version: 1.0
 */
@Data
@Configuration
@ConditionalOnExpression("!'${ignore}'.isEmpty()")
@ConfigurationProperties(prefix = GlobalsConstants.FILTER_IGNORE)
public class FilterIgnoreProperties {
	/**
	 * 放行终端配置，网关不校验此处的终端
	 */
	private List<String> clients = new ArrayList<>();
	/**
	 * 放行url,放行的url不再被安全框架拦截
	 */
	private List<String> urls = new ArrayList<>();
	/**
	 * 不聚合swagger
	 */
	private List<String> swaggerProviders = new ArrayList<>();
}
