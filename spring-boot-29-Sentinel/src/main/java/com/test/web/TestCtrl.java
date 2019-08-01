package com.test.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.test.service.TestService;
import com.test.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCtrl
{

    @Autowired
    private TestService service;


    @GetMapping(value = "/rest")
    @ResponseBody
    public String rest()
    {
        return  service.rest();
    }


    @GetMapping(value = "/hello")
    @SentinelResource(value = "user", blockHandler = "getUserInfoFallback", fallback = "getUserInfoFallback")
    @RequestMapping
    public JSONObject getUserInfo(Integer id) throws InterruptedException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", "zm");
        jsonObject.put("age", "20");
        Thread.sleep(100L);
        return jsonObject;
    }

    public static JSONObject getUserInfoFallback(Integer id) throws InterruptedException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", "zmmm");
        jsonObject.put("age", "188");
        return jsonObject;
    }
}
