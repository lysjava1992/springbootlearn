package com.springboot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    HelloProperties helloProperties;
    @Bean
    public HelloService helloService(){
        HelloService service=new HelloService();
        if(helloProperties.getSuffix()==null){
            helloProperties.setSuffix("!欢迎来到SpringBoot");
        }
        if(helloProperties.getPrefix()==null){
            helloProperties.setPrefix("你好");

        }
        service.setHelloProperties(helloProperties);
        return service;
    }

}




















