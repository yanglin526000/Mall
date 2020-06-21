package com.mall.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
//@MapperScan(basePackages = {"com.mall.goods.dao"})
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
