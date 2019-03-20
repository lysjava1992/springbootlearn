package com.springboot.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.springboot.redis.dao")
@SpringBootApplication
@EnableCaching//开启缓存注解
public class SpringBootIntegrationRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntegrationRedisApplication.class, args);
	}

}
