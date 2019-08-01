package com.test.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zoum
 * @create 2019/5/7 11:29
 */
public class UserControllerFallback {


    public static JSONObject getUserInfoFallback(Integer id) throws InterruptedException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", "zmmm");
        jsonObject.put("age", "188");
        return jsonObject;
    }
}
