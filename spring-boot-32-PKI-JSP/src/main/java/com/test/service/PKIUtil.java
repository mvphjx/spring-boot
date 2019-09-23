package com.test.service;

import javax.servlet.http.HttpServletRequest;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PKIUtil
{
    public static String getShenfenID(HttpServletRequest request)
    {
        String cardNo = null;
        X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
        if (certs == null)
        {
            System.out.println("对不起！系统没有找到用户证书！请您插入key后重新刷新页面！");
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
            }
            catch (Exception e)
            {
                System.out.println("解析证书失败：" + e.toString());
                cardNo = sn;
            }
        }
        return cardNo;
    }
}
