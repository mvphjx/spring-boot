package com.test.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.util.Date;
import java.util.Random;
import java.util.ServiceConfigurationError;
import java.util.logging.Logger;

@Service
public class TestService
{

    @Autowired
    private RestTemplate restTemplate;

    //随机运行时间
    @HystrixCommand(commandKey="TestService.timeOut",fallbackMethod = "fallback", threadPoolProperties = {
            //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
            @HystrixProperty(name = "coreSize", value = "5"), @HystrixProperty(name = "maxQueueSize", value = "30"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "10") }, commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"), //命令执行超时时间
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),//若3s一个窗口内失败三次, 则达到触发熔断的最少请求量
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") })
    public String timeOut()
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
    public String exception()
    {
        Thread th=Thread.currentThread();
        System.out.println(th.getName());
        int i = new Random().nextInt(20);
        if (i <= 10)
        {
            throw new RuntimeException("突然出现了一个异常");
        }else if(i<=15){
            throw new ServiceConfigurationError("这是一个error");
        }
        String url = "http://www.baidu.com/";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    public String fallback()
    {
        System.out.println("快速失败:"+System.currentTimeMillis());
        return "快速失败"+new Date();
    }

}
