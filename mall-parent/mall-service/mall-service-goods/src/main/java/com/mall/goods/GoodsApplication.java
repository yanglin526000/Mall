package com.mall.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <p>
 * Goods Application
 * </p>
 *
 * @author yanglin
 * @date 2020-06-21 15:10:14
 */
@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackages = "com.mall.pojo")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
