package com.spark.platform.common.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.config.mybatis
 * @ClassName: MybatisPlusConfig
 * @Author: wangdingfeng
 * @Description: MybatisPlus配置
 * @Date: 2020/3/16 13:57
 * @Version: 1.0
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
