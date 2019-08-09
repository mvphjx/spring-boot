package com.han.service.support.abis10;

import com.han.spi.IAresBaseService;
import com.han.spi.data.AresMessageObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractAres10Service implements IAresBaseService
{
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 通用检索
     *
     * @param systemId
     * @param dataType
     * @param subDBName
     * @return
     */
    public AresMessageObject search(String systemId, String dataType, String subDBName)
    {
        //使用abis10.0通用接口  封装成为SPI接口
        return null;
    }

    ;

    /**
     * 其他通用方法
     *
     * @return
     */
    @HystrixCommand(commandKey = "abis10-test", fallbackMethod = "fallback")
    public String test()
    {
        return timeOut();
    }

    //随机运行时间
    private String timeOut()
    {
        System.out.println(Thread.currentThread().getName());
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
        }
        return "abis10 common";
    }

}
