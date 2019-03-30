package cn.timebusker.utils;



import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
public class HttpTest
{
    @Test
    public void get()
    {
        String s = HttpClientUtil.get("http://127.0.0.1:8080/aop/tp/1");
        System.out.println(s);
    }
    @Test
    public void post()
    {
        Map<String, Object> params = new HashMap<>();
        params.put("data","检视反馈数据");
        String json = "{\"data\":\"检视反馈数据\"}";
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json;charset=utf-8");
        String s = HttpClientUtil.post("http://127.0.0.1:8080/aop/siyue", json, header);
        System.out.println(s);
    }
}
