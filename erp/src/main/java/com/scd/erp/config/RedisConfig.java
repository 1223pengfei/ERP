package com.scd.erp.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig  extends CachingConfigurerSupport {
    @Value("${spring.redis.timeout}")
    private Duration timeToLive = Duration.ZERO;

    @Bean(name = "redisTemplate")
    @Primary
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        System.out.println("开启Redis配置");
        RedisTemplate<String, String> template = new RedisTemplate<>();
        RedisSerializer valueRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        RedisSerializer keyRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(keyRedisSerializer);
        template.setValueSerializer(valueRedisSerializer);
        template.setHashKeySerializer(keyRedisSerializer);
        template.setHashValueSerializer(valueRedisSerializer);
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        return template;

    }

    @Bean(name = "cacheManager")
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();
        return  RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
    }

}

