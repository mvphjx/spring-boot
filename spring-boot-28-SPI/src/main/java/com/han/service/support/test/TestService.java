package com.han.service.support.test;

import com.han.spi.IAresService;
import com.han.spi.data.AresMessageObject;
import com.han.spi.data.AresServiceInfo;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.metric.HystrixCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@ConditionalOnClass
//当配置 test.enabled=true 时加载
@ConditionalOnProperty(prefix = "test ", value = "enabled", havingValue = "true")
public class TestService implements IAresService
{

    @Autowired
    private RestTemplate restTemplate;
    private AresServiceInfo info;

    @Override
    public AresServiceInfo getServiceInfo()
    {
        if (info == null)
        {
            info = new AresServiceInfo();
            info.setSystemId("test");
        }
        return info;
    }

    @Override
    @HystrixCommand(commandKey = "TestService.test", fallbackMethod = "fallback", threadPoolProperties = {
            //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
            @HystrixProperty(name = "coreSize", value = "5"), @HystrixProperty(name = "maxQueueSize", value = "50"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20") }, commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"), //命令执行超时时间
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),//若3s一个窗口内失败三次, 则达到触发熔断的最少请求量
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") })
    public String test() throws Exception
    {
        try
        {
            return exception();
        }
        catch (Exception e)
        {
            System.out.println("exception():" + e.getMessage());
            throw e;
        }

    }

    @Override
    public AresMessageObject search(String systemId, String dataType, String subDBName)
    {
        return null;
    }

    //随机异常
    private String exception() throws Exception
    {
        System.out.println(Thread.currentThread().getName());
        int i = new Random().nextInt(20);
        if (i <= 20)
        {
            throw new Exception("突然出现了一个异常");
        }
        String url = "http://www.baidu.com/";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    private String fallback()
    {
        return "访问超时，快速失败";
    }
}
