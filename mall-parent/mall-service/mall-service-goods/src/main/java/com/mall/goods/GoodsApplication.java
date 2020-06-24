package com.mall.goods;

import com.mall.common.utils.ConstantUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

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
@ComponentScan(basePackages = {ConstantUtil.SWAGGER2_PACKAGE_NAME, "com.mall.goods"})
@EntityScan(basePackages = "com.mall.pojo")
public class GoodsApplication {
    /**
     * <p>
     * Main
     * </p>
     *
     * @param args
     * @author yanglin
     * @date 2020-06-24 21:30:11
     */
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
