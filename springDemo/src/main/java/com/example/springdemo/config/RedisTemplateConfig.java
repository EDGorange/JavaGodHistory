package com.example.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2024-11-28 16:45
 **/
@Configuration
public class RedisTemplateConfig {


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置键的序列化器，推荐使用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 根据实际需求设置值的序列化器等其他属性
        return redisTemplate;
    }
}
