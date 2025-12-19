<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SSSHOP</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div id="container">
    <h1>岳伟商城</h1>

    <c:forEach items="${productList}" var="product">
        <div class="item">
            <a href="show?id=${product.id}" class="product-link">
                <div class="product-image">
                    <img src="${product.imgUrl}">
                </div>
                <div class="product-info">${product.description}</div>
                <div class="product-price">${product.price}</div>
            </a>
        </div>
    </c:forEach>

    <form action="shop" method="get">
        <button type="submit" >结算</button>
    </form>
</div>
</body>
</html>