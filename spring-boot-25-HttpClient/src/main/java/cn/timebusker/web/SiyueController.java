package cn.timebusker.web;

import cn.timebusker.service.CommonService;
import cn.timebusker.service.order.OrderInfoService;
import cn.timebusker.service.user.UserInfoService;
import cn.timebusker.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SiyueController
{

    private final static Logger logger = LoggerFactory.getLogger(SiyueController.class);

    @Resource
    CommonService service;

    @Resource
    OrderInfoService orderService;

    @Resource
    UserInfoService userService;

    @RequestMapping(value = { "/siyue/hit" }, method = RequestMethod.POST)
    @ResponseBody
    public HashMap testSiyueAopKey(@RequestBody Map<String, String> map)
    {
        logger.info("\n=======================思越 服务器检视接口========================\n");
        logger.info("接受来自指纹系统参数为");
        if (map != null)
        {
            for (String key : map.keySet())
            {
                System.out.println("Key = " + key);
                System.out.println("Value = " + map.get(key));
            }
        }
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("status", "error");
        return hashMap;
    }

    @RequestMapping(value = { "/siyue/tp" }, method = RequestMethod.GET)
    @ResponseBody
    public HashMap testSiyueAopKey(@RequestParam String personId,@RequestParam String fgp)
    {
        logger.info("\n=======================思越 服务器捺印接口========================\n");
        logger.info("接受来自指纹系统参数为");
        logger.info(personId,fgp);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("status", "ok");
        hashMap.put("data", "思越的捺印数据*"+personId+"*"+fgp);
        return hashMap;
    }
}
