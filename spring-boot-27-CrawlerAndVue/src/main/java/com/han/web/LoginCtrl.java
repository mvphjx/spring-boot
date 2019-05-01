package com.han.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginCtrl
{
    @RequestMapping(value = {"/user/login"}, method = RequestMethod.POST)
    public Map login(@RequestBody Map<String,Object> params) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",20000);
        map.put("data","token");
        return map;
    }

    @RequestMapping(value = {"/user/info"}, method = RequestMethod.GET)
    public Map info() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",20000);
        HashMap<String , Object> info = new HashMap<>();
        String[] rolses = {"admin"};
        info.put("roles",rolses);
        info.put("introduction","I am a super administrator");
        info.put("avatar","");
        info.put("name","韩健祥");
        map.put("data",info);
        return map;
    }
}
