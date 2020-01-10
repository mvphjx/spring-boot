package com.svn.stat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/*
 SpringBootServletInitializer
 这个类的作用与在web.xml中配置负责初始化Spring应用上下文的监听器作用类似，
 如果需要打成war部署在tomcat下则需要
 */
@SpringBootApplication
public class SvnStatApp
{
    private final static Logger logger = LoggerFactory.getLogger(SvnStatApp.class);

    public static void main(String[] args)
    {
        // 启动Sprign Boot
        ApplicationContext ctx = SpringApplication.run(SvnStatApp.class, args);
    }
}
