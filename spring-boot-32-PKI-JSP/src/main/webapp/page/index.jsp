<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PKI登录测试</title>
</head>
<body>



<form method="post" action="" id="loginForm"></form>
<input type="button" id="loginFormButton" value="PKI 数字证书登录测试"></input>
<br>
<input type="button" id="pkiHome" value="打开首页验证PKI信息 "></input>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script>
    var host = window.location.host;
    host = host.substr(0, host.indexOf(":"));
    var preUrl = "https://" + host + ":8443/test";
    var $iframe = $('#pkiIframe');
    $("#loginFormButton").click(function(){
        $("#loginForm").attr("action",preUrl + "/pkiPage/");
        $("#loginForm").submit();
    });
    $("#pkiHome").click(function () {
        window.open("${pageContext.request.contextPath}/home/", "_blank")
    });


</script>
</body>
</html>