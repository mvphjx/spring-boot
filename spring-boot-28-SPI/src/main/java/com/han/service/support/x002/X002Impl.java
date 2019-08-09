package com.han.service.support.x002;

import com.han.spi.IAresTPService;
import com.han.spi.data.AresMessageObject;
import com.han.spi.data.AresServiceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * 自动配置类
 *
 * @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置。
 * @ConditionalOnMissingBean，当Spring Context中不存在该Bean时。
 * @ConditionalOnProperty(prefix = "calculate ",value = "enabled",havingValue = "true")，
 * 当配置文件中calculate.enabled=true时。
 */
@Service("x002")
@ConditionalOnClass
public class X002Impl implements IAresTPService
{
    private AresServiceInfo info;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AresServiceInfo getServiceInfo()
    {
        if (info == null)
        {
            info = new AresServiceInfo();
            info.setSystemId("x002");
        }
        return info;
    }

    @Override
    public AresMessageObject search(String systemId, String dataType, String subDBName)
    {
        return null;
    }

    //随机延迟
    private String getInfo()
    {
        System.out.println(Thread.currentThread().getName());
        int i = new Random().nextInt(1000);
        try
        {
            Thread.sleep(i);
        }
        catch (InterruptedException e)
        {

        }
        String url = "http://www.baidu.com/";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    private String fallback()
    {
        return "服务超时";
    }

    /***
     * 重要！！！
     * 如果 HystrixCommand注解  在内层方法  例如 private String getInfo（）上，将不会被代理，配置无效
     * @return
     */
    @HystrixCommand(commandKey="X002Impl",fallbackMethod = "fallback", threadPoolProperties = {
            //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
            @HystrixProperty(name = "coreSize", value = "10"),
            //BlockingQueue的最大队列数，当设为-1，会使用SynchronousQueue，值为正时使用LinkedBlcokingQueue
            @HystrixProperty(name = "maxQueueSize", value = "100"),
            //设置队列拒绝的阈值,即使maxQueueSize还没有达到
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20") }, commandProperties = {
            //当出错率超过50%后熔断器启动
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            //统计滚动的时间窗口，超时时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
            //打开断路器的最少请求数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
            // 熔断器工作时间（服务恢复间隔），超过这个时间，先放一个请求进去，成功的话就关闭熔断，失败就再等一段时间
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") })
    @Override
    public String test()
    {
        return this.getInfo();
    }
}
