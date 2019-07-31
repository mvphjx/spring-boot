package com.han.service.x001;

import com.han.spi.IAresService;
import com.han.spi.data.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自动配置类
 *
 * @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
 * @ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
 * @ConditionalOnProperty(prefix = "calculate ",value = "enabled",havingValue = "true")，
 * 当配置文件中calculate.enabled=true时。
 */
@Component("x001")
//关联配置类
@EnableConfigurationProperties(X001Properties.class)
//当classpath下发现该类的情况下进行自动配置
@ConditionalOnClass
//当配置 x001.enabled=true 时加载
@ConditionalOnProperty(prefix = "x001 ", value = "enabled", havingValue = "true")
public class X001Impl implements IAresService
{
    private ServiceInfo info;
    @Autowired
    private X001Properties properties;

    @Override
    public ServiceInfo getServiceInfo()
    {
        if (info == null)
        {
            info = new ServiceInfo();
            info.setSystemId("X001Impl");
            System.out.println("properties:"+properties);
        }
        return info;
    }
}
