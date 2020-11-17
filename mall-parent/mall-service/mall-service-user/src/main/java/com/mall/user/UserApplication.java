package com.mall.user;

import com.mall.common.utils.ConstantUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;

/**
 * <p>
 * UserApplication
 * </p>
 *
 * @author yanglin
 * @date 2020-11-17 23:49:13
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScans(value = {
        @ComponentScan(
                excludeFilters = {@ComponentScan.Filter(
                        type = FilterType.CUSTOM,
                        classes = {TypeExcludeFilter.class}
                ), @ComponentScan.Filter(
                        type = FilterType.CUSTOM,
                        classes = {AutoConfigurationExcludeFilter.class}
                )}),
        @ComponentScan(basePackages = {ConstantUtil.COMMON_PACKAGE_NAME})
})
@EntityScan(basePackages = ConstantUtil.USER_PACKAGE_NAME)
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
