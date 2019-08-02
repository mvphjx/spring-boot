package com.test.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class TestService
{

    @Autowired
    private RestTemplate restTemplate;
    //随机运行时间
    public String timeOut()
    {
        int i = new Random().nextInt(500);
        try
        {
            Thread.sleep(i);
        }
        catch (InterruptedException e)
        {
            System.out.println("sleep time:" + i);
        }
        String url = "http://www.baidu.com/";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    //随机异常
    @SentinelResource(value = "exception", blockHandler = "fallback", fallback = "fallback")
    public String exception() throws Exception
    {
        int i = new Random().nextInt(20);
        if (i <= 10)
        {
            System.out.println("exception突然出现了一个异常:"+i);
            throw new Exception("exception突然出现了一个异常");
        }else{
            System.out.println("exception正常请求:"+i);
        }
        String url = "http://www.baidu.com/";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }
    public String fallback()
    {
        return "快速失败";
    }
}
