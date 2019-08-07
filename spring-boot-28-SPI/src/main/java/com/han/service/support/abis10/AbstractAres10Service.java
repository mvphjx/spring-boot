package com.han.service.support.abis10;

import com.han.spi.IAresBaseService;
import com.han.spi.data.AresMessageObject;

public abstract  class AbstractAres10Service implements IAresBaseService
{
    /**
     * 通用检索
     * @param systemId
     * @param dataType
     * @param subDBName
     * @return
     */
    public AresMessageObject search(String systemId, String dataType, String subDBName){
        //使用abis10.0通用接口  封裝成為SPI接口
        return null;
    };

    /**
     * 其他通用方法
     * @return
     */
    public String test(){
        return "abis10 common";
    };


}
