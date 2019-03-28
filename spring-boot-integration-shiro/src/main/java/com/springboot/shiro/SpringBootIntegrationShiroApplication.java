package com.springboot.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.springboot.shiro.dao")
@SpringBootApplication
public class SpringBootIntegrationShiroApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntegrationShiroApplication.class, args);
	}
}
