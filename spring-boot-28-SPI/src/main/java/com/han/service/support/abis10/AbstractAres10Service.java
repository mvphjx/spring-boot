package com.han.service.support.abis10;

import com.han.spi.IAresBaseService;
import com.han.spi.data.AresMessageObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            //获取人员信息
            String tpUrl = "http://192.168.129.150:7950/abisweb/hitlog/addtllt/getTPCard/6666";
            HttpHeaders requestHeaders = new HttpHeaders();
            //设置登录后的cookie
            requestHeaders.put("Cookie", getCookie());
            MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
            HttpEntity<?> requestEntity = new HttpEntity<Object>(body, requestHeaders);
            ResponseEntity<Map> responseEntity = restTemplate
                    .exchange(tpUrl, HttpMethod.POST, requestEntity, Map.class);
            Map body1 = responseEntity.getBody();
            return body1.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 获取登录后的  票据
     *
     * @return
     */
    private List<String> getCookie()
    {
        Map<String, Object> data = new HashMap();
        data.put("userName", "ll007");
        data.put("password", "11223344");
        //登录
        ResponseEntity<String> loginResponseEntity = restTemplate
                .postForEntity("http://192.168.129.150:7950/abisweb/login/dologin", data, String.class);
        HttpHeaders headers = loginResponseEntity.getHeaders();
        return headers.get("Set-Cookie");

    }

}
