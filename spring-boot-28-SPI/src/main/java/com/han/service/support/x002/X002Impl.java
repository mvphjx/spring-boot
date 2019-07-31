package com.han.service.support.x002;

import com.han.spi.IAresService;
import com.han.spi.data.AresServiceInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * 自动配置类
 *
 * @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
 * @ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
 * @ConditionalOnProperty(prefix = "calculate ",value = "enabled",havingValue = "true")，
 * 当配置文件中calculate.enabled=true时。
 */
@Component("x002")
@ConditionalOnClass
public class X002Impl implements IAresService
{
    private AresServiceInfo info;


    @Override
    public AresServiceInfo getServiceInfo()
    {
        if (info == null)
        {
            info = new AresServiceInfo();
            info.setSystemId("X002Impl");
        }
        return info;
    }
}
