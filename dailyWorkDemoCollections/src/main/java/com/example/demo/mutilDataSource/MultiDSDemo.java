/*
package com.example.demo.mutilDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

*/
/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-06-26 14:02
 **//*

@Configuration
public class MultiDSDemo {
    @Bean("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }
}

public class RoutingDataSource extends AbstractRoutingDataSource{

}*/
