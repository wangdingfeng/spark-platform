package com.spark.platform.common.security.config;

import com.spark.platform.common.security.support.SparkAccessDeniedHandler;
import com.spark.platform.common.security.support.SparkAuthExceptionEntryPoint;
import com.spark.platform.common.security.properties.FilterIgnoreProperties;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.config
 * @ClassName: SophiaResourceServerConfig
 * @Description: 资源服务
 * 优先级低于AuthorizationServerConfigurerAdapter
 * @Version: 1.0
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SparkResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private FilterIgnoreProperties ignorePropertiesConfig;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    @SneakyThrows
    public void configure(HttpSecurity httpSecurity) {
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        httpSecurity.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
                .ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        //对配置的url放行 不进行验证
        ignorePropertiesConfig.getUrls()
                .forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and().csrf().disable();
    }

    /**
     * token store
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }


    @Override
    @CrossOrigin
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore())
                //自定义Token异常信息,用于token校验失败返回信息
                .authenticationEntryPoint(new SparkAuthExceptionEntryPoint())
                //授权异常处理
                .accessDeniedHandler(new SparkAccessDeniedHandler());
    }

}
