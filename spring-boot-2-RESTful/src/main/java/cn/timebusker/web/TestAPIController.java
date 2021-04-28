package cn.timebusker.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestAPIController
{

    @RequestMapping(value = { "/produces1" }, method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
    @ResponseBody
    public Map produces1()
    {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status", "ok");
        return map;
    }

    @RequestMapping(value = { "/produces2" }, method = RequestMethod.GET)
    public void produces2(HttpServletResponse response)
    {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status", "ok");
        response.setContentType("text/html");
        try (OutputStream ros = response.getOutputStream())
        {
            String s = JSON.toJSONString(map);
            ros.write(s.getBytes());
            ros.flush();
        }
        catch (IOException e)
        {
        }
    }
}
