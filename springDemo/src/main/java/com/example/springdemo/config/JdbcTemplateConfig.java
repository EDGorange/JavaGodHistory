package com.example.springdemo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-08-18 10:17
 **/
@Configuration
public class JdbcTemplateConfig {
    @Bean
    JdbcTemplate jdbcTemplateMysql(@Qualifier("dsMySql") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    JdbcTemplate jdbcTemplateOracle(@Qualifier("dsOracle") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
