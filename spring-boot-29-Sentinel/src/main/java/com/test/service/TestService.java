package com.test.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.test.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestService
{

    @Autowired
    private RestTemplate restTemplate;

    // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 static 函数.
    @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = { ExceptionUtil.class })
    public void test() throws Exception
    {
        System.out.println("test通过");
        //
    }

    public String rest()
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

    // 原函数
    @SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    public String hello(long s) throws Exception
    {
        return String.format("Hello通过", s);
    }

    // Fallback 函数，函数签名与原函数一致.
    public String helloFallback(long s)
    {
        return String.format("helloFallbackhelloFallback", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public static String exceptionHandler(long s, BlockException ex)
    {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }
}
