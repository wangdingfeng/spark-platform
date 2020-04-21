package com.spark.platform.auth.config;

import com.spark.platform.common.security.config.SparkResourceServerConfig;
import com.spark.platform.common.security.properties.SparkSecurityProperties;
import com.spark.platform.common.security.service.SparkUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.auth.config
 * @ClassName: SparkWebSecurityConfig
 * @Description: web security 访问安全配置
 * @Version: 1.0
 */
@EnableWebSecurity
@Configuration
@AutoConfigureBefore({SparkResourceServerConfig.class, SparkAuthorizationServerConfig.class})
public class SparkWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SparkUserDetailService sparkUserDetailService;

    @Autowired
    private SparkSecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers(securityProperties.getWeb().getUnInterceptUris())
                .permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sparkUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
