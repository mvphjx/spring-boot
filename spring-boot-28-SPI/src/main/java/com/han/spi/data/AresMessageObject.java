package com.han.spi.data;

public class AresMessageObject
{
    private AresServiceInfo serviceInfo;
    private String status;
    private Object result;

    public AresServiceInfo getServiceInfo()
    {
        return serviceInfo;
    }

    public void setServiceInfo(AresServiceInfo serviceInfo)
    {
        this.serviceInfo = serviceInfo;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Object getResult()
    {
        return result;
    }

    public void setResult(Object result)
    {
        this.result = result;
    }
}
