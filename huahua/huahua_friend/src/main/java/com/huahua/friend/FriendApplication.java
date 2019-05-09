package com.huahua.friend;

import huahua.common.utils.IdWorker;
import huahua.common.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient  //申明使当前的服务注册到eureka中
@EnableFeignClients
@EnableDiscoveryClient
public class FriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class,args);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }

    @Bean
    public BCryptPasswordEncoder bc(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

}
