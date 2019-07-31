package com.han.service.manage;

import com.han.spi.IAresService;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * spi 服务模块管理
 */
@Component
public class ServiceMgr
{
    //服务模块缓存
    private HashMap<String, IAresService> _classMappings = new HashMap<String, IAresService>();

    /**
     * 根据配置文件 模块初始化
     */
    public void init() throws Exception
    {
        addService("1_1_Wuxi", Class.forName("com.hisign.pu.abis.ares.spi.impl.xzpt.LPBaseInfoService"));
    }

    /**
     * 获取服务模块
     *
     * @param systemId  系统ID
     * @param dataType  数据类型
     * @param subDBName 子库
     * @return
     */
    public IAresService getService(String systemId, String dataType, String subDBName)
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
    private void addService(String key, Class forClass) throws IllegalAccessException, InstantiationException
    {

        _classMappings.put(key, (IAresService) forClass.newInstance());
    }

    private String getKey(String systemId, String dataType, String subDBName)
    {
        return systemId + "_" + dataType + "_" + subDBName;
    }

}
