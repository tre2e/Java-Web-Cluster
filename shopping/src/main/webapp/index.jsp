<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

    <%--  广告嵌入  --%>
    <div class="ad-container">
        <jsp:include page="/ad" flush="true" />  <!-- 触发 Servlet 获取数据 -->

        <c:if test="${not empty resourceUrl}">
            <c:set var="urlLower" value="${fn:toLowerCase(resourceUrl)}" />

            <c:choose>
                <c:when test="${fn:endsWith(urlLower, '.jpg') || fn:endsWith(urlLower, '.jpeg') ||
                            fn:endsWith(urlLower, '.png') || fn:endsWith(urlLower, '.gif') ||
                            fn:endsWith(urlLower, '.webp')}">
                    <img src="${resourceUrl}" alt="${adTitle}" class="ad-image" />
                </c:when>

                <c:when test="${fn:endsWith(urlLower, '.mp4') || fn:endsWith(urlLower, '.webm')}">
                    <video class="ad-video" controls autoplay muted loop playsinline>
                        <source src="${resourceUrl}" type="video/mp4">
                        您的浏览器不支持视频。
                    </video>
                </c:when>

                <c:otherwise>
                    <!-- 兜底：用 iframe 显示 H5 广告或未知类型 -->
                    <iframe src="${resourceUrl}" class="ad-iframe" frameborder="0"></iframe>
                </c:otherwise>
            </c:choose>

            <c:if test="${not empty adTitle}">
                <p class="ad-title">${adTitle}</p>
            </c:if>
        </c:if>
    </div>

    <form action="shop" method="get">
        <button type="submit" >结算</button>
    </form>
</div>
</body>
</html>