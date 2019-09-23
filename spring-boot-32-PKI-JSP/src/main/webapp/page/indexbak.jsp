<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PKI登录测试</title>
</head>
<body>

<%
    String ip = request.getHeader("x-forwarded-for");
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
    }
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
    }
%>

<form method="post" action="https://<%=ip%>:8443/test/pki/" id="loginForm">
    <button type="submit">PKI 数字证书登录</button>
</form>

<br>
<%--<input type="button" id="pkiButton" value="PKI 数字证书登录(ajax) "></input>--%>


<iframe id="pkiIframe" src='about:blank'></iframe>
<input type="button" id="pkiButtonIframePost" value="PKI 数字证书登录(iframe post) "></input>
<input type="button" id="pkiButtonIframeGet" value="PKI 数字证书登录(iframe get) "></input>
<input type="button" id="pkiHome" value="打开首页验证PKI信息 "></input>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script>
    $("#pkiButton").click(function(){
        var host = window.location.host;
        host = host.substr(0, host.indexOf(":"));
        var url = "https://" + host + ":8443/test/pki/";
        $.ajax
        (
            {
                type: 'POST',
                contentType: 'application/json',
                url: url,
                success: function (data) {
                    alert(data)
                },
                error: function (e) {
                    alert(e)
                }
            }
        );
    });
    $("#pkiButtonIframePost").click(function(){
        var host = window.location.host;
        host = host.substr(0, host.indexOf(":"));
        var url = "https://" + host + ":8443/test/pki/";
        var $iframe = $('#pkiIframe');
        var iframe_doc = $iframe[0].contentWindow || $iframe[0].contentDocument;
        if (iframe_doc.document) {
            iframe_doc = iframe_doc.document;
        }
        var iframe_html = "<form method=\"post\" action=\""+url
            +"\">\n" +
            "    <button type=\"submit\">PKI 数字证书登录</button>\n" +
            "</form>";
        iframe_doc.write(iframe_html);
        $(iframe_doc).find('form').submit();
        //window.location.href="${pageContext.request.contextPath}/home/"
    });


    $("#pkiButtonIframeGet").click(function(){
        var host = window.location.host;
        host = host.substr(0, host.indexOf(":"));
        var url = "https://" + host + ":8443/test/pki/";
        var $iframe = $('#pkiIframe');
        var iframe_doc = $iframe[0].contentWindow || $iframe[0].contentDocument;
        if (iframe_doc.document) {
            iframe_doc = iframe_doc.document;
        }
        var iframe_html = "<form method=\"post\" action=\""+url
            +"\">\n" +
            "    <button type=\"submit\">PKI 数字证书登录</button>\n" +
            "</form>";
        iframe_doc.write(iframe_html);
        $(iframe_doc).find('form').submit();
        //window.location.href="${pageContext.request.contextPath}/home/"
    });
    $("#pkiHome").click(function(){
      window.open("${pageContext.request.contextPath}/home/","_blank")
    });


</script>
</body>
</html>