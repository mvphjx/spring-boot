package com.test.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.test.service.TestService;
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


    @GetMapping(value = "/exception") @ResponseBody
    public String rest() throws Exception
    {
        return  service.exception();
    }


    @GetMapping(value = "/timeOut")
    @SentinelResource(value = "timeOut", blockHandler = "getUserInfoFallback", fallback = "getUserInfoFallback")
    @RequestMapping
    public JSONObject getUserInfo(Integer id)
    {
        service.timeOut();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", "zm");
        jsonObject.put("age", "20");
        return jsonObject;
    }

    public static JSONObject getUserInfoFallback(Integer id)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", "null");
        jsonObject.put("age", "-1");
        return jsonObject;
    }
}
