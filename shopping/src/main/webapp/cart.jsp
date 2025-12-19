<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>购物车 - 岳伟商城</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div id="container">
    <h1>我的购物车</h1>

    <c:choose>
        <c:when test="${empty sessionScope.cart}">
            <div class="empty-cart">
                购物车还是空的，赶紧去
                <a href="${pageContext.request.contextPath}/index.jsp">挑选商品</a> 吧！
            </div>
        </c:when>

        <c:otherwise>
            <table class="cart-table">
                <thead>
                <tr>
                    <th>商品图</th>
                    <th>商品名称</th>
                    <th>描述</th>
                    <th>单价</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.cart}" var="item">
                    <tr>
                        <td><img src="${item.imgUrl}" class="cart-item-img" alt="${item.name}"></td>
                        <td class="cart-item-name">${item.name}</td>
                        <td class="cart-item-desc">${item.description}</td>
                        <td class="cart-item-price">¥${item.price}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="cart-actions">
                <a href="${pageContext.request.contextPath}/shop?action=clear"
                   class="btn-clear"
                   onclick="return confirm('确定清空购物车吗？')">
                    清空购物车
                </a>
            </div>

            <div class="cart-summary">
                共 <span class="cart-count">${sessionScope.cart.size()}</span> 件商品
            </div>

            <div class="cart-continue">
                <a href="${pageContext.request.contextPath}/index.jsp" class="btn-continue">
                    继续购物
                </a>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="cart-footer">
        <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
    </div>
</div>

</body>
</html>