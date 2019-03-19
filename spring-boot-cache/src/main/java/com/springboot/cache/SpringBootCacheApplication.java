package com.springboot.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
/**
 * 开启基于注解的缓存
 *  @CacheEvict
 *  @CacePut
 *  @Cacheable //缓存
 */
@EnableCaching
@MapperScan("com.springboot.cache.dao")
@SpringBootApplication
public class SpringBootCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheApplication.class, args);
	}

}
