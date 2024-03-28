package com.example.springdemo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2024-03-28 19:21
 **/
@Configuration
public class DatasourceTranstaionManager {

    @Bean(name = "dataSourceTransactionManagerPrimary")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManagerPrimary(@Qualifier ("dsMySql")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
