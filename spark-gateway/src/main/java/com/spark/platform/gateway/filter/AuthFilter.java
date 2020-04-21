package com.spark.platform.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.gateway.filter
 * @ClassName: AuthFilter
 * @Description: Spring Cloud Gateway的全局过滤器GlobalFilter，顾名思义，
 * 声明后会对所有的请求生效，可以用来做权限控制，这里简单记录一下拦截到非法请求后如何返回自定义信息和将请求重定向到指定URL
 * @Version: 1.0
 */
@Component
public class AuthFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        System.out.println(exchange.getRequest().getURI());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
