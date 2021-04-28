package com.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 可以通过调用  有参构造方法
 * 实现多实例
 */
@Service
@Scope("prototype")
@ConditionalOnProperty(prefix = "service.test", value = "enabled", havingValue = "true")
public class TestService implements IService
{

    private final static Logger logger = LoggerFactory.getLogger(TestService.class);
    private int extSysId = 10;

    public TestService(int extSysId){
        logger.info("执行有参构造方法:"+extSysId);
        this.extSysId = extSysId;
    }

    public TestService()
    {
        logger.info("执行无参构造方法:"+extSysId);
        //根据配置 注入多个IService
    }

    @Override
    public int getExtSysId()
    {
        return extSysId;
    }
}
