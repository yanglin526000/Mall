package com.mall.file;

import com.mall.common.swagger2.Swagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
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
        @ComponentScan(basePackageClasses = {Swagger2.class})
})
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class);
    }
}
