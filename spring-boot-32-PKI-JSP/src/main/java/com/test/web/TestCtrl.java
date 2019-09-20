package com.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("")
public class TestCtrl
{
    @Autowired
    private HttpSession session;

    @GetMapping(value = "/page/")
    public String page(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("sessionID:"+session.getId());
        session.setAttribute("pkiInfo", "证书信息为空");
        return "index";
    }

    @GetMapping(value = "/home/")
    public String detail(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("sessionID:"+session.getId());
        Object pkiInfo = session.getAttribute("pkiInfo");
        System.out.println(pkiInfo);
        return "home";
    }

    @RequestMapping(value = "/pki/")
    public String pkiLogin(HttpServletRequest request, HttpServletResponse response)
    {
        String result = "home";
        String service = request.getParameter("service");
        String cardNo = null;
        X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
        if (certs == null)
        {
            System.out.println("对不起！系统没有找到用户证书！请您插入key后重新刷新页面！");
            return "error";
        }
        else
        {
            // 验证证书，获取验证返回信息
            X509Certificate gaX509Cert = null;
            gaX509Cert = certs[0];
            // 获取序列号
            String sn = gaX509Cert.getSerialNumber().toString(16);
            System.out.println("当前证书信息是：" + sn);
            try
            {
                String tempDN = gaX509Cert.getSubjectDN().toString(); // 取DN
                String[] strDN = tempDN.split(",");
                String strID[] = strDN[0].split("=");
                // 如果没有警员号，把身份证号作为username
                if (strID[1].indexOf("@") != -1)
                {
                    String tempDn = gaX509Cert.getSubjectDN().toString(); // 取DN
                    String[] card = tempDn.split(",");
                    // 获取身份证号
                    String cardStr = card[1];
                    String regex = "[0-9]*([0-9]|X|x)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(cardStr);
                    if (matcher.find())
                    {
                        cardNo = matcher.group();
                    }
                }
                else
                {
                    // 获取身份证号
                    String cardStr = strID[1];
                    String regex = "[0-9]*([0-9]|X|x)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(cardStr);
                    if (matcher.find())
                    {
                        cardNo = matcher.group();
                    }
                    cardNo = strID[1].substring(strID[1].indexOf(" ")).trim();
                }
                request.getSession().setAttribute("pkiInfo", "证书信息：" + strDN[0] + "********" + cardNo);
                result = "home";
            }
            catch (Exception e)
            {
                result = "error";
            }
        }
        return "index";
    }

}
