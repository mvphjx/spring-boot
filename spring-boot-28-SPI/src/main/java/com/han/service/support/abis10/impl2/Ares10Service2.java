package com.han.service.support.abis10.impl2;

import com.han.service.support.abis10.AbstractAres10Service;
import com.han.spi.data.AresServiceInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnClass
public class Ares10Service2 extends AbstractAres10Service
{
    private AresServiceInfo info;
    @Override
    public AresServiceInfo getServiceInfo()
    {
        if (info == null)
        {
            info = new AresServiceInfo();
            info.setSystemId("Abis10Service2");
        }
        return info;
    }
    public String fallback()
    {
        return "快速失败Abis10Service2";
    }
}
