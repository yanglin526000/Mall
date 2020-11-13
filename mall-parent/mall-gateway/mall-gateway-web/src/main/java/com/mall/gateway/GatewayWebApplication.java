package com.mall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <p>
 * Gateway Web Application
 * </p>
 *
 * @author yanglin
 * @date 2020-11-05 23:21:25
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class, args);
    }

    /**
     * <p>
     * ip 限流操作
     * </p>
     *
     * @return org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
     * @author yanglin
     * @date 2020-11-13 23:39:44
     */
    @Bean(name = "ipKeyResolver")
    public KeyResolver keyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                //1.获取请求request对象
                ServerHttpRequest request = exchange.getRequest();
                //2.从request中获取ip地址
                String hostString = request.getRemoteAddress().getHostString();//Ip地址
                //3.返回
                return Mono.just(hostString);
            }
        };
    }
}
