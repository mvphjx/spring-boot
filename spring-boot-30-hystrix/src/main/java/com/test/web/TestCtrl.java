package com.test.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCtrl
{

    @Autowired
    private TestService service;

    @GetMapping(value = "/timeOut")
    @ResponseBody
    public String timeOut()
    {
        return service.timeOut();
    }

    @HystrixCommand(fallbackMethod = "fallback", threadPoolProperties = {
            //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
            @HystrixProperty(name = "coreSize", value = "10"),
            //BlockingQueue的最大队列数，当设为-1，会使用SynchronousQueue，值为正时使用LinkedBlcokingQueue
            @HystrixProperty(name = "maxQueueSize", value = "100"),
            //设置队列拒绝的阈值,即使maxQueueSize还没有达到
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20") }, commandProperties = {
            //当出错率超过50%后熔断器启动
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            //统计滚动的时间窗口，超时时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            //打开断路器的最少请求数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
            // 熔断器工作时间（服务恢复间隔），超过这个时间，先放一个请求进去，成功的话就关闭熔断，失败就再等一段时间
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") })
    @GetMapping(value = "/exception")
    @ResponseBody
    public String user() throws Exception
    {
        return service.exception();
    }

    public String fallback()
    {
        return "快速失败";
    }

}
