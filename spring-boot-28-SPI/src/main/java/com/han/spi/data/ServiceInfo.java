package com.han.spi.data;
/**
 *  服务信息描述
 * @author 北京海鑫高科指纹技术有限公司<br>
 *         北京海鑫科金高科技股份有限公司<br>
 *         www.idfounder.com<br>
 *         www.hisign.com.cn<br>
 *         创建日期：   2019/7/30 13:54
 */
public class ServiceInfo
{
    private String systemId;

    public String getSystemId()
    {
        return systemId;
    }

    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    @Override
    public String toString()
    {
        return "ServiceInfo{" + "systemId='" + systemId + '\'' + '}';
    }
}
