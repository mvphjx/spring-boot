package com.han.spi.data;

/**
 * 服务信息描述
 *
 * @author 北京海鑫高科指纹技术有限公司<br>
 * 北京海鑫科金高科技股份有限公司<br>
 * www.idfounder.com<br>
 * www.hisign.com.cn<br>
 * 创建日期：   2019/7/30 13:54
 */
public class AresServiceInfo
{
    private String systemId="";
    private String dataType="";
    private String subDBName="";

    public String getSystemId()
    {
        return systemId;
    }

    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public String getSubDBName()
    {
        return subDBName;
    }

    public void setSubDBName(String subDBName)
    {
        this.subDBName = subDBName;
    }

    @Override
    public String toString()
    {
        return "AresServiceInfo{" + "systemId='" + systemId + '\'' + '}';
    }
}
