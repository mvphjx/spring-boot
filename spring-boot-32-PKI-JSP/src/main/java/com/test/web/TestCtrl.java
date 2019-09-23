package com.test.web;

import com.test.service.PKIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class TestCtrl
{
    @Autowired
    private HttpSession session;

    private static String KEY = "pkiInfo";

    @GetMapping(value = "/page/")
    public String page(HttpServletRequest request, HttpServletResponse response)
    {
        Object attribute = session.getAttribute(KEY);
        if (attribute == null)
        {
            session.setAttribute("pkiInfo", "证书信息为空");
        }
        System.out.println("/page/ sessionID:" + session.getId());
        System.out.println("session-" + KEY + "值:" + session.getAttribute(KEY));
        return "index";
    }

    @GetMapping(value = "/home/")
    public String home(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("/home/ sessionID:" + session.getId());
        System.out.println("session-" + KEY + "值:" + session.getAttribute(KEY));
        return "home";
    }

    @RequestMapping(value = "/pkiPage/")
    public String pkiLoginPage(HttpServletRequest request, HttpServletResponse response)
    {
        String shenfenID = PKIUtil.getShenfenID(request);
        request.getSession().setAttribute("pkiInfo", shenfenID);
        System.out.println("/pkiPage/ sessionID:" + session.getId());
        System.out.println("session-" + KEY + "值:" + session.getAttribute(KEY));
        Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        String redirectUrl = "http://" + request.getServerName() + ":7955" + request.getContextPath() + "/home/";
        System.out.println(redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/pki/")
    @ResponseBody
    public String pkiLogin(HttpServletRequest request, HttpServletResponse response)
    {
        String shenfenID = PKIUtil.getShenfenID(request);
        session.setAttribute("pkiInfo", shenfenID);
        System.out.println("/pki/ sessionID:" + session.getId());
        System.out.println("session-" + KEY + "值:" + session.getAttribute(KEY));
        return shenfenID;
    }

    @RequestMapping(value = "/status/")
    @ResponseBody
    public Map status(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("/status/ sessionID:" + session.getId());
        System.out.println("session-" + KEY + "值:" + session.getAttribute(KEY));
        Object attribute = session.getAttribute(KEY);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "当前session状态:" + attribute);
        return map;
    }
}
