package com.han;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableCircuitBreaker
public class AresApp
{
    public static void main(String[] args)
    {
        // 启动Sprign Boot
        ApplicationContext ctx = SpringApplication.run(AresApp.class, args);
    }
    //装载 restTemplate
    @Bean
    @LoadBalanced
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
