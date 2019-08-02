package com.han.service.manage;

import com.han.spi.IAresBaseService;
import com.han.spi.IAresService;
import com.han.spi.data.AresServiceInfo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * Ares服务管理组件
 */
@Component
public class ServiceMgr
{
    @Autowired
    private ApplicationContext applicationContext;

    //服务模块缓存
    private HashMap<String, IAresBaseService> _classMappings = new HashMap<String, IAresBaseService>();

    /**
     * 根据注解初始化
     * 注册服务
     */
    @PostConstruct
    private void init() throws BeansException
    {
        System.out.println("\n\n-----------------Registry Ares Service-----------------------");
        String[] beanNamesForType = applicationContext.getBeanNamesForType(IAresBaseService.class);
        for (String s : beanNamesForType)
        {
            IAresBaseService aresService = (IAresBaseService) applicationContext.getBean(s);
            AresServiceInfo serviceInfo = aresService.getServiceInfo();
            System.out.println(serviceInfo);
            String key = getKey(serviceInfo.getSystemId(), serviceInfo.getDataType(), serviceInfo.getSubDBName());
            _classMappings.put(key, aresService);
        }
    }

    /**
     * 获取服务模块
     *
     * @param systemId  系统ID
     * @param dataType  数据类型
     * @param subDBName 子库
     * @return
     */
    public IAresBaseService getService(String systemId, String dataType, String subDBName)
    {
        String key = getKey(systemId, dataType, subDBName);
        return _classMappings.get(key);
    }

    /**
     * 增加服务模块
     *
     * @param key
     * @param forClass
     */
    private void addService(String key, Class<? extends IAresService> forClass)
            throws IllegalAccessException, InstantiationException
    {

        _classMappings.put(key, forClass.newInstance());
    }

    private String getKey(String systemId, String dataType, String subDBName)
    {
        return systemId + "_" + dataType + "_" + subDBName;
    }

}
