package com.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestSingleService implements IService
{

    private final static Logger logger = LoggerFactory.getLogger(TestSingleService.class);
    private int extSysId = 1;

    public TestSingleService(ApplicationContext ctx)
    {
        logger.info("执行有参构造方法:" + ctx);
        //根据配置 注入多个IService
    }

    @Override
    public int getExtSysId()
    {
        return extSysId;
    }
}
