<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>购物首页</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="detail-container">
    <div class="detail-img">
        <img src="${p.imgUrl}" alt="${p.name}">
    </div>

    <div class="detail-info">
        <div class="detail-name">${p.name}</div>
        <div class="detail-desc">${p.description}</div>
        <div class="detail-price">¥${p.price}</div>

        <c:if test="${not empty addMsg}">
            <div class="add-success-msg">${addMsg}</div>
        </c:if>
        <div>
            <form action="shop" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="id" value="${p.id}">
                <button type="submit" >
                    加入购物车
                </button>
            </form>
        </div>

        <a href="index.jsp" class="back-btn">返回商品列表</a>
    </div>
</div>
</body>
</html>
