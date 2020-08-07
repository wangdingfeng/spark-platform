package com.spark.platform.control.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.control.config
 * @ClassName: WebSecurityConfigurer
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/8/1 10:04
 * @Version: 1.0
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public WebSecurityConfigurer(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http
                .headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers(adminContextPath + "/assets/**"
                        , adminContextPath + "/login"
                        , adminContextPath + "/actuator/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContextPath + "/login")
                .successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout")
                .and()
                .httpBasic().and()
                .csrf()
                .disable();
        // @formatter:on
    }

}
