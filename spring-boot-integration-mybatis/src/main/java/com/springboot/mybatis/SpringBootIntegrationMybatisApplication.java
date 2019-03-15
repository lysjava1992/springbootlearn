package com.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.springboot.mybatis.dao") //配置dao层的扫描文件
@SpringBootApplication
public class SpringBootIntegrationMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntegrationMybatisApplication.class, args);
	}

}
