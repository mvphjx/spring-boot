package cn.timebusker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@CrossOrigin
@Controller
@RequestMapping("/demo")
public class Demo
{
    {
        System.out.println("\n\ndemo init\n\n");
    }
    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
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
