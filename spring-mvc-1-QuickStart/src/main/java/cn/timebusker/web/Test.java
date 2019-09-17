package cn.timebusker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/")
public class Test
{
    {
        System.out.println("\n\nTest init\n\n");
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String QueryCommonListMgr() throws Exception
    {
        return "13113213";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() throws Exception
    {
        return "13113213";
    }
}
