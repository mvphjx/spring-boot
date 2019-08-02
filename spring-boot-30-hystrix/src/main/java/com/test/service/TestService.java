package com.test.service;

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
    public String user()
    {
        int i = new Random().nextInt(4000);
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
    public String exception() throws Exception
    {
        int i = new Random().nextInt(20);
        if (i <= 10)
        {
            throw new Exception("突然出现了一个异常");
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
