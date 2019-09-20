package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/*
 SpringBootServletInitializer
 这个类的作用与在web.xml中配置负责初始化Spring应用上下文的监听器作用类似，
 如果需要打成war部署在tomcat下则需要
 */
@SpringBootApplication
public class PkiJspApp extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PkiJspApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PkiJspApp.class, args);
    }
}
