<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PKI登录成功</title>
</head>
<body>
<div>信息如下：</div>
${sessionScope.pkiInfo}
<%=session.getAttribute("pkiInfo")%>
</body>
</html>