package com.han.service.support.x001;

import com.han.spi.IAresService;
import com.han.spi.data.AresMessageObject;
import com.han.spi.data.AresServiceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * 自动配置类
 *
 * @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
 * @ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
 * @ConditionalOnProperty(prefix = "calculate ",value = "enabled",havingValue = "true")，
 * 当配置文件中calculate.enabled=true时。
 */
@Service("x001")
//关联配置类
@EnableConfigurationProperties(X001Properties.class)
//当classpath下发现该类的情况下进行自动配置
@ConditionalOnClass
//当配置 x001.enabled=true 时加载
@ConditionalOnProperty(prefix = "x001 ", value = "enabled", havingValue = "true")
public class X001Impl implements IAresService
{
    @Autowired
    private RestTemplate restTemplate;

    private AresServiceInfo info;
    @Autowired
    private X001Properties properties;

    @Override
    public AresServiceInfo getServiceInfo()
    {
        if (info == null)
        {
            info = new AresServiceInfo();
            info.setSystemId("x001");
            System.out.println("properties:" + properties);
        }
        return info;
    }

    @Override
    public AresMessageObject search(String systemId, String dataType, String subDBName)
    {
        return null;
    }

    @Override
    public String test()
    {
        return null;
    }

}
