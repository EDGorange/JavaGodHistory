package com.example.springdemo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-08-18 10:12
 **/
@Configuration
public class DataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.mysql")
    DataSource dsMySql() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.oracle")
    DataSource dsOracle() {
        return DruidDataSourceBuilder.create().build();
    }


}
