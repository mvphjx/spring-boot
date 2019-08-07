package com.han.spi;

import com.han.spi.data.AresMessageObject;
import com.han.spi.data.AresServiceInfo;

public interface IAresBaseService
{
    /**
     * 获取服务信息
     * @return
     */
    AresServiceInfo getServiceInfo();

    /**
     * 通用检索
     * @param systemId
     * @param dataType
     * @param subDBName
     * @return
     */
    AresMessageObject search(String systemId, String dataType, String subDBName);

    /**
     * 其他通用方法
     * @return
     */
    String test();

}
