package com.han.service.support.x001;

import com.han.spi.IAresService;
import com.han.spi.data.AresServiceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 自动配置类
 *
 * @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
 * @ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
 * @ConditionalOnProperty(prefix = "calculate ",value = "enabled",havingValue = "true")，
 * 当配置文件中calculate.enabled=true时。
 */
@Service("x001")
//关联配置类
@EnableConfigurationProperties(X001Properties.class)
//当classpath下发现该类的情况下进行自动配置
@ConditionalOnClass
//当配置 x001.enabled=true 时加载
@ConditionalOnProperty(prefix = "x001 ", value = "enabled", havingValue = "true")
public class X001Impl implements IAresService
{
    @Autowired
    private RestTemplate restTemplate;

    private AresServiceInfo info;
    @Autowired
    private X001Properties properties;

    @Override
    public AresServiceInfo getServiceInfo()
    {
        if (info == null)
        {
            info = new AresServiceInfo();
            info.setSystemId("X001Impl");
            System.out.println("properties:" + properties);
        }
        return info;
    }

    @HystrixCommand(fallbackMethod = "fallback", threadPoolProperties = {
            //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
            @HystrixProperty(name = "coreSize", value = "10"), @HystrixProperty(name = "maxQueueSize", value = "100"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20") }, commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"), //命令执行超时时间
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),//若3s一个窗口内失败三次, 则达到触发熔断的最少请求量
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "30000") //断路30s后尝试执行, 默认为5s
    })
    public String getInfo()
    {
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        String url = "http://www.baidu.com/";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    public String fallback()
    {
        return "服务超时";
    }

    @Override
    public String test()
    {
        return this.getInfo();
    }
}
