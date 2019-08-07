package com.test.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class DemoService
{

    @Autowired
    private RestTemplate restTemplate;

    //随机运行时间
    @HystrixCommand(commandKey="DemoService.timeOut",fallbackMethod = "fallback", threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "15"), @HystrixProperty(name = "maxQueueSize", value = "100"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "100") }, commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"), //命令执行超时时间
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),//若3s一个窗口内失败三次, 则达到触发熔断的最少请求量
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") })
    public String timeOut()
    {

        String url = LocalDateTime.now().toString();
        return url;
    }

    //随机异常
    public String exception() throws Exception
    {
        Thread th=Thread.currentThread();
        System.out.println(th.getName());
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
