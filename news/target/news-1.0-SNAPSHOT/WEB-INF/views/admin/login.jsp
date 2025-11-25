<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>管理员登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="container">
    <h2>管理员登录</h2>
    <form method="post" action="${pageContext.request.contextPath}/admin/login">
        <div>
            <label> <input type="text" name="username" placeholder="请输入用户名" required></label>
        </div>
        <div style="margin-top: 10px;">
            <label> <input type="password" name="password" placeholder="请输入密码" required></label>
        </div>
        <div>
        <button type="submit" style="margin-top: 20px;">登录</button>
        </div>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </form>
</div>
</body>
</html>
