package com.mall.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * <p>
 * Common DataSource Config
 * 在同样的DataSource中，首先使用被标注的DataSource
 * </p>
 *
 * @author yanglin
 * @date 2020-07-02 00:28:47
 */
@Configuration
@Primary
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * <p>
     * Init Data Source
     * </p>
     *
     * @return javax.sql.DataSource
     * @author yanglin
     * @date 2020-07-02 00:20:43
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        try {
            Class.forName(driverClassName);
            String url01 = datasourceUrl.substring(0, datasourceUrl.indexOf("?"));
            String url02 = url01.substring(0, url01.lastIndexOf("/"));
            String datasourceName = url01.substring(url01.lastIndexOf("/") + 1);
            // 连接已经存在的数据库，如：mysql
            Connection connection = DriverManager.getConnection(url02, username, password);
            Statement statement = connection.createStatement();
            // 创建数据库
            statement.executeUpdate("create database if not exists `" + datasourceName + "` default character set utf8 COLLATE utf8_general_ci");
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

}
